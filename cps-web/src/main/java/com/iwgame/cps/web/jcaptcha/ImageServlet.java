/****************************************************************
 * 文件名 : ImageServlet.java
 * 日期 : 2013-6-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.web.jcaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-29 上午9:44:08
 * @版本: v1.0.0
 */
public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7345072202357564385L;

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);

		int length = 4;
		if (request.getParameter("len") != null) {
			length = Integer.valueOf(request.getParameter("len")).intValue();
		}
		String allstring = "abcdefghijklmnopqrstuvwxyz0123456789";
		String numstring = "0123456789";
		String base;
		if (request.getParameter("onlynum") != null) {
			if (Boolean.valueOf(request.getParameter("onlynum")).booleanValue()) {
				base = numstring;
			} else {
				base = allstring;
			}
		} else {
			base = allstring;
		}

		int width = 15 * length;
		int height = 22;

		BufferedImage image = new BufferedImage(width, height, 1);

		Graphics g = image.createGraphics();

		g.setColor(getRandColor(180, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Tahoma", 0, 17));

		Random rand = new Random();

		int size = base.length();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int start = rand.nextInt(size);
			String tmpStr = base.substring(start, start + 1);

			str.append(tmpStr);

			g.setColor(getRandColor(10, 150));

			g.drawString(tmpStr, 13 * i + 6 + rand.nextInt(5), 14 + rand.nextInt(6));
		}

		request.getSession().setAttribute("valiCode", str.toString());

		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
