package hieuboy.admin.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
@RequestMapping(value = "admin/revenue")
public class Revenue1Controller {

    @Autowired
    protected SessionFactory sessionFactory;

	@RequestMapping(method = RequestMethod.GET)
	public String homeRevenue() {
		return "admin/revenue-home";
	}

	// Doanh thu theo danh mục
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "by-category")
	public String byCategory(ModelMap model, @RequestParam(defaultValue = "01/01/2018") Date min,
							 @RequestParam(defaultValue = "31/12/2900") Date max) {

		String hql = "SELECT od.product.category.name, " + "SUM(od.quantity),"
				+ "SUM(od.quantity*od.price*(1-od.discount))," + "MIN(od.price), MAX(od.price)," + " AVG(od.price) "
				+ "FROM OrderItem od " + "WHERE od.order.status=3 and od.order.createAt BETWEEN :min AND :max "
				+ "GROUP BY od.product.category.name";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("min", min);
		query.setParameter("max", max);
		List<Object[]> list = query.list();
		model.addAttribute("arrays", list);
		return "admin/revenue";
	}

	// Doanh thu theo tháng
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "by-month")
	public String byMonth(ModelMap model, @RequestParam(defaultValue = "01/01/2018") Date min,
						  @RequestParam(defaultValue = "31/12/2900") Date max) {
		String hql = "SELECT " + "MONTH(od.order.createAt)," + "SUM(od.quantity), "
				+ "SUM(od.quantity*od.quantity*(1-od.discount)), " + "MIN(od.quantity), " + "MAX(od.quantity), "
				+ "AVG(od.quantity) " + "FROM OrderItem od " + "WHERE od.order.status=4 and od.order.createAt BETWEEN :min AND :max "
				+ "GROUP BY MONTH(od.order.createAt)";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("min", min);
		query.setParameter("max", max);
		List<Object[]> list = query.list();
		model.addAttribute("arrays", list);
		return "admin/revenue";
	}

	// Doanh thu theo quý
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "by-quarter")
	public String byQuarter(ModelMap model, @RequestParam(defaultValue = "01/01/2018") Date min,
							@RequestParam(defaultValue = "31/12/2900") Date max) {
		String hql = "SELECT " + "CAST(CEILING(MONTH(od.order.createAt)/3.0) as integer )," + "SUM(od.quantity), "
				+ "SUM(od.quantity*od.price*(1-od.discount)), " + "MIN(od.price), " + "MAX(od.price), "
				+ "AVG(od.price) " + "FROM OrderItem od " + "WHERE od.order.status=3 and od.order.createAt BETWEEN :min AND :max "
				+ "GROUP BY CAST(CEILING(MONTH(od.order.createAt)/3.0) as integer)";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("min", min);
		query.setParameter("max", max);
		List<Object[]> list = query.list();
		model.addAttribute("arrays", list);
		return "admin/revenue";
	}

//	// Doanh thu theo năm
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "by-year")
//	public String byYear(ModelMap model, @RequestParam(defaultValue = "01/01/2018") Date min,
//						 @RequestParam(defaultValue = "31/12/2900") Date max) {
//		String hql = "SELECT " + "YEAR(od.order.orderDate)," + "SUM(od.quantity), "
//				+ "SUM(od.quantity*od.amount*(1-od.discount)), " + "MIN(od.amount), " + "MAX(od.amount), "
//				+ "AVG(od.amount) " + "FROM OrderDetail od " + "WHERE od.order.status=4 and od.order.orderDate BETWEEN :min AND :max "
//				+ "GROUP BY YEAR(od.order.orderDate)";
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery(hql);
//		query.setParameter("min", min);
//		query.setParameter("max", max);
//		List<Object[]> list = query.list();
//		model.addAttribute("arrays", list);
//		return "admin/revenue";
//	}

}
