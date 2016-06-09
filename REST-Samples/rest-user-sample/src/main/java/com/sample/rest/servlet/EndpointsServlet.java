package com.sample.rest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.core.ResourceInvoker;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ResourceMethodRegistry;
import org.jboss.resteasy.spi.Registry;

public class EndpointsServlet extends HttpServlet {

	private static final long serialVersionUID = 3589752697161098546L;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JaxRsResource> resources = getAllResources();
		Iterator<JaxRsResource> it = resources.iterator();
		StringBuffer buff = new StringBuffer("<html><head><title>REST Endpoints</title></head><body>");
		buff.append("<center><B>REST Endpoints</B><br/><br/>");
		buff.append("<table align='left' border=1 cellspacing=0 cellpadding=0 width='100%''>");
		buff.append("<tr align='left' valign='middle' bgcolor='#808080'>");
		buff.append("<th>Class</th>");
		buff.append("<th>HTTP Method</th>");
		buff.append("<th>Class Method</th>");
		buff.append("<th>Endpoint URL</th></tr>");

		String clazz = "";
		int cnt = 0;
		while (it.hasNext()) {
			StringBuffer clazzColumn = new StringBuffer();
			JaxRsResource resource = it.next();
			if (!clazz.equals(resource.clazz)) {
				clazzColumn.append("<td rowspan='").append(getRowSpan(resources, resource.clazz)).append("'>").append(resource.clazz).append("</td>");
				clazz = resource.clazz;
				cnt++;
			}

			buff.append("<tr align='left' valign='middle' ").append((cnt % 2 == 0 ? "bgcolor='#D0D0D0'" : "")).append(">");
			buff.append(clazzColumn);
			buff.append("<td>").append(resource.htttpMethod).append("</td>");
			buff.append("<td>").append(resource.clazzMethod).append("</td>");
			buff.append("<td>").append(resource.uri).append("</td>");
			buff.append("</tr>");
		}
		buff.append("</table></body></html>");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.write(buff.toString());
		pw.close();
	}

	private int getRowSpan(List<JaxRsResource> resources, String clazz) {
		int rowSpan = 0;
		Iterator<JaxRsResource> it = resources.iterator();
		while (it.hasNext()) {
			JaxRsResource resource = it.next();
			if (clazz.equals(resource.clazz)) {
				rowSpan++;
			}
		}
		return rowSpan;
	}

	public void destroy() {
		super.destroy();
	}

	public List<JaxRsResource> getAllResources() {
		List<JaxRsResource> resources = new ArrayList<JaxRsResource>();
		ResourceMethodRegistry registry = (ResourceMethodRegistry) getServletContext().getAttribute(Registry.class.getName());
		for (Map.Entry<String, List<ResourceInvoker>> entry : registry.getBounded().entrySet()) {
			for (ResourceInvoker rinvoker : entry.getValue()) {
				ResourceMethodInvoker invoker = (ResourceMethodInvoker) rinvoker;
				Method method = invoker.getMethod();

				if (method.getDeclaringClass() == getClass()) {
					continue;
				}

				for (String verb : invoker.getHttpMethods()) {
					String uri = entry.getKey();
					resources.add(new JaxRsResource(verb, uri, invoker.getResourceClass().getSimpleName(), method.getName()));
				}
			}
		}
		Collections.sort(resources);
		return resources;
	}

	public static final class JaxRsResource implements Comparable<JaxRsResource> {
		String htttpMethod;
		String uri;
		String clazz;
		String clazzMethod;

		public JaxRsResource() {
		}

		public JaxRsResource(String htttpMethod, String uri, String clazz, String clazzMethod) {
			this.htttpMethod = htttpMethod;
			this.uri = uri;
			this.clazz = clazz;
			this.clazzMethod = clazzMethod;
		}

		public int compareTo(JaxRsResource o) {
			int cnt = clazz.compareTo(o.clazz);
			if (cnt != 0) {
				return cnt;
			}
			return htttpMethod.compareTo(o.htttpMethod);

		}
	}
}
