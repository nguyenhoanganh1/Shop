package com.eshop.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.eshop.dao.ReportDAO;
import com.eshop.entity.Report;

@Controller
@CrossOrigin
@RequestMapping("/admin/report")
public class ReportAController {
	@Autowired
	ReportDAO rdao;

	/*
	 * @RequestMapping("inventory-by-category") public String
	 * inventoryByCategory(Model model) { return
	 * "admin/report/inventory-by-category"; }
	 * 
	 * @RequestMapping("revenue-by-category") public String revenueByCategory(Model
	 * model) {
	 * 
	 * return "admin/report/revenue-by-category"; }
	 * 
	 * @RequestMapping("revenue-by-customer") public String revenueByCustomer(Model
	 * model) { return "admin/report/revenue-by-customer"; }
	 * 
	 * @RequestMapping("revenue-by-year") public String revenueByYear(Model model) {
	 * return "admin/report/revenue-by-year"; }
	 * 
	 * @RequestMapping("revenue-by-quarter") public String revenueByQuarter(Model
	 * model) { return "admin/report/revenue-by-quarter"; }
	 * 
	 * @RequestMapping("revenue-by-month") public String revenueByMonth(Model model)
	 * { return "admin/report/revenue-by-month"; }
	 */

	@RequestMapping("chart")
	public String chart(Model model) {
		return "admin/report/chart";
	}

	@RequestMapping("index")
	public String index(Model model) {
		return "admin/report/index";
	}

	@ResponseBody
	@RequestMapping("summary")
	public Map<Integer, Long> summary() {
		return rdao.getSummary();
	}

	@ResponseBody
	@RequestMapping("{name}")
	public List<Report> reports(@PathVariable("name") String name) {
		switch (name) {
		case "inventory-by-category":
			return rdao.getInventoryByCategory();
		case "revenue-by-category":
			return rdao.getRevenueByCategory();
		case "revenue-by-customer":
			return rdao.getRevenueByCustomer(PageRequest.of(0, 10));
		case "revenue-by-year":
			return rdao.getRevenueByYear();
		case "revenue-by-quarter":
			return rdao.getRevenueByQuarter();
		case "revenue-by-month":
			return rdao.getRevenueByMonth();
		}
		return null;
	}

	@GetMapping("/export/{name}")
	public void exportToCSV(OutputStream outputStream, HttpServletResponse resp, @PathVariable("name") String name)
			throws IOException {
		resp.setContentType("text/csv");
		String fileName = "";
		String headerKey = "Content-Disposition";
		switch (name) {
		case "inventory-by-category": {
			fileName = name + ".csv";
			String headerValue = "attachment; filename=" + fileName;
			resp.setHeader(headerKey, headerValue);
			List<Report> reports = rdao.getInventoryByCategory();
			Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			writer.write('\uFEFF');
			// resp.getWriter()
			
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Tên Loại Sản Phẩm", 
									"Tổng Giá trị", 
									"Số lượng", 
									"Giá thấp nhất", 
									"Giá cao nhất",
									"Giá trung bình" };
			String[] nameMapping = { "group", "sum", "count", "min", "max", "avg" };

			beanWriter.writeHeader(csvHeader);
			for (Report report : reports) {
				beanWriter.write(report, nameMapping);
			}
			beanWriter.close();
		}

		case "revenue-by-category": {
			fileName = name + ".csv";
			String headerValue = "attachment; filename=" + fileName;
			resp.setHeader(headerKey, headerValue);
			List<Report> reports = rdao.getRevenueByCategory();
			Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			writer.write('\uFEFF');
			// resp.getWriter()
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Tên Loại Sản Phẩm", "Tổng Giá trị", "Số lượng", "Giá thấp nhất", "Giá cao nhất",
					"Giá trung bình" };
			String[] nameMapping = { "group", "sum", "count", "min", "max", "avg" };

			beanWriter.writeHeader(csvHeader);
			for (Report report : reports) {
				beanWriter.write(report, nameMapping);
			}
			beanWriter.close();
		}

		case "revenue-by-customer": {
			fileName = name + ".csv";
			String headerValue = "attachment; filename=" + fileName;
			resp.setHeader(headerKey, headerValue);
			List<Report> reports = rdao.getRevenueByCustomer(PageRequest.of(0, 10));
			Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			writer.write('\uFEFF');
			// resp.getWriter()
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Tên khách hàng", "Tổng Giá trị", "Số lượng", "Giá thấp nhất", "Giá cao nhất",
					"Giá trung bình" };
			String[] nameMapping = { "group", "sum", "count", "min", "max", "avg" };

			beanWriter.writeHeader(csvHeader);
			for (Report report : reports) {
				beanWriter.write(report, nameMapping);
			}
			beanWriter.close();
		}

		case "revenue-by-year": {
			fileName = name + ".csv";
			String headerValue = "attachment; filename=" + fileName;
			resp.setHeader(headerKey, headerValue);
			List<Report> reports = rdao.getRevenueByYear();
			Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			writer.write('\uFEFF');
			// resp.getWriter()
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Năm", "Tổng Giá trị", "Số lượng", "Giá thấp nhất", "Giá cao nhất",
					"Giá trung bình" };
			String[] nameMapping = { "group", "sum", "count", "min", "max", "avg" };

			beanWriter.writeHeader(csvHeader);
			for (Report report : reports) {
				beanWriter.write(report, nameMapping);
			}
			beanWriter.close();

		}

		case "revenue-by-quarter": {
			fileName = name + ".csv";
			String headerValue = "attachment; filename=" + fileName;
			resp.setHeader(headerKey, headerValue);
			List<Report> reports = rdao.getRevenueByQuarter();
			Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			writer.write('\uFEFF');
			// resp.getWriter()
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Quý", "Tổng Giá trị", "Số lượng", "Giá thấp nhất", "Giá cao nhất",
					"Giá trung bình" };
			String[] nameMapping = { "group", "sum", "count", "min", "max", "avg" };

			beanWriter.writeHeader(csvHeader);
			for (Report report : reports) {
				beanWriter.write(report, nameMapping);
			}
			beanWriter.close();

		}

		case "revenue-by-month": {
			fileName = name + ".csv";
			String headerValue = "attachment; filename=" + fileName;
			resp.setHeader(headerKey, headerValue);
			List<Report> reports = rdao.getRevenueByMonth();
			Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			writer.write('\uFEFF');
			// resp.getWriter()
			ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			String[] csvHeader = { "Tháng", "Tổng Giá trị", "Số lượng", "Giá thấp nhất", "Giá cao nhất",
					"Giá trung bình" };
			String[] nameMapping = { "group", "sum", "count", "min", "max", "avg" };

			beanWriter.writeHeader(csvHeader);
			for (Report report : reports) {
				beanWriter.write(report, nameMapping);
			}
			beanWriter.close();
			
		}

		}
	}
}
