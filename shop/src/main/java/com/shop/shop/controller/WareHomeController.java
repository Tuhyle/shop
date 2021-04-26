package com.shop.shop.controller;

import com.shop.shop.entity.Product;
import com.shop.shop.entity.WareHome;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.repository.WareHouseRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Lê Thị Thúy
 * @created 4/25/2021
 * @project shop
 */
@Controller
@RequestMapping(value = "/admin")
public class WareHomeController {
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    ProductRepository productRepository;
    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            WareHome wareHome = new WareHome();

            XSSFRow row = worksheet.getRow(i);

            wareHome.setId(null);
            wareHome.setNameProduct(row.getCell(1).getStringCellValue());
            wareHome.setQuantity((int) row.getCell(2).getNumericCellValue());
            wareHome.setPrice((double) row.getCell(3).getNumericCellValue());
            wareHome.setDetail(row.getCell(4).getStringCellValue());
            wareHome.setCreateAt(new Date());
            wareHouseRepository.save(wareHome);
        }
        return "redirect:/admin/warehouseList";
    }
    @GetMapping("/warehouseList")
    public String viewCategory(Model model) {
        List<WareHome> wareHomes = wareHouseRepository.findAll();
        model.addAttribute("wareHomes", wareHomes);
        return "admin/ware_home";
    }
    @GetMapping(value = "/edit_warehouse/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model) {
        Optional<WareHome> warehouse=wareHouseRepository.findById(id);
        warehouse.get().setId(id);
        warehouse.get().setUpdateAt(new Date());
        model.addAttribute("warehouse",warehouse );
        return "/admin/edit_warehouse";
    }
    @PostMapping("/edit_warehouse")
    public String editCategory(@ModelAttribute WareHome wareHome) {
        wareHouseRepository.save(wareHome);
        return "redirect:/admin/warehouseList";
    }
    @PostMapping("/add_warehouse" )
    public String saveCategory(@ModelAttribute WareHome wareHome) {
        if (wareHome != null) {
            wareHouseRepository.save(wareHome);
            wareHome.setCreateAt(new Date());
            return "redirect:/admin/warehouseList";
        }
        return null;
    }
    //add category
    @GetMapping(value = "/add_warehouse")
    public String addCategory(Model model) {
        model.addAttribute("warehouse", new WareHome());
        return "admin/add_warehouse";
    }
    @GetMapping(value = "/delete_warehouse/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        Optional<WareHome> warehouse=wareHouseRepository.findById(id);
        List<Product> products=productRepository.findAllByWareHomeId(id);
        for (Product item: products) {
            productRepository.deleteById(item.getId());
        }
        wareHouseRepository.deleteById(id);
        return "redirect:/admin/warehouseList";
    }
}
