package cn.xh.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xh.domain.Book;
import cn.xh.domain.Category;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;
import cn.xh.service.ClientService;
import cn.xh.service.ManagerService;
import cn.xh.service.impl.ClientServiceImpl;
import cn.xh.service.impl.ManagerServiceImpl;
import cn.xh.web.formbean.Cart;
import cn.xh.web.formbean.CartItem;

@WebServlet("/client/ClientServlet")
public class ClientServlet extends HttpServlet {
	private final ClientService service = new ClientServiceImpl();
	private final ManagerService managerService = new ManagerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String op = req.getParameter("op");// 得到传过来的请求
		if (op != null && !op.equals("")) {
			// 登录
			if (op.equals("login")) {
				login(req, resp);
			}
			// 注销
			if (op.equals("layout")) {
				layout(req, resp);
			}
			// 注册
			if (op.equals("register")) {
				register(req, resp);
			}
			// 文学艺术类选课列表
			if (op.equals("category")) {
				getCategoryBook(req, resp);
			}

			// 个人信息修改
			if (op.equals("personInformation")) {
				personInformation(req, resp);
			}
			// 修改密码
			if (op.equals("personPassword")) {
				personPassword(req, resp);
			}
			// 搜索框
			if (op.equals("search")) {
				search(req, resp);
			}
			// 详情页面
			if (op.equals("particulars")) {
				particulars(req, resp);
			}
			//放视频
			if (op.equals("video")) {
				video(req, resp);
			}
			// 添加已选课程
			if (op.equals("addCart")) {
				addCart(req, resp);
			}
			// 删除已选课程中的购物项
			if (op.equals("delItem")) {
				delItem(req, resp);
			}
			// 修改购物项数量
			if (op.equals("changeNum")) {
				changeNum(req, resp);
			}
			// 添加收藏夹
			if (op.equals("addfavorite")) {
				addfavorite(req, resp);
			}
			// 显示收藏夹
			if (op.equals("showfavorite")) {
				showfavorite(req, resp);
			}
			// 删除收藏夹
			if (op.equals("delFavorite")) {
				delFavorite(req, resp);
			}
			// 删除收藏夹
			if (op.equals("buyNow")) {
				buNow(req, resp);
			}

		}
	}

	private void delFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String book_id = req.getParameter("book_id");
		service.delFavorite(book_id);
		HttpSession session = req.getSession();
		List<Favorite> lists = (List<Favorite>) session.getAttribute("favorite");
		Iterator<Favorite> iterator = lists.iterator();
		while (iterator.hasNext()) {
			Favorite favorite = iterator.next();
			if (book_id.equals(favorite.getBook().getBook_id())) {
				iterator.remove();// 使用迭代器的删除方法删除
			}
		}
		resp.sendRedirect(req.getContextPath() + "/favorite.jsp");
	}

	private void showfavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<Favorite> favorites = service.findFavoriteByUserId(user);
		session.setAttribute("favorite", favorites);
		req.getRequestDispatcher("/favorite.jsp").forward(req, resp);
	}

	private void addfavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		String book_id = req.getParameter("book_id");
		boolean isExit = service.findFavorite(user_id, book_id);
		if (isExit == false) {
			service.addfavorite(user_id, book_id);
		}
	}

	private void changeNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String num = req.getParameter("num");
		String book_id = req.getParameter("book_id");
		// 取出已选课程
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		CartItem item = cart.getItmes().get(book_id);
		item.setQuantity(Integer.parseInt(num));
		resp.sendRedirect(req.getContextPath() + "/showCart.jsp");

	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		User user = service.login(username, password);
		if (user.getUsername() != null && user.getUsername() != "") {
			req.setAttribute("message", "登陆成功");
			session.setAttribute("user", user);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "用户名或密码错误，请重新登录");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}

	private void layout(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("user");// 获取session对象，从session中移除登陆信息
			resp.sendRedirect("/client/ClientServlet?op=category");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			String tel = req.getParameter("tel");
			String address = req.getParameter("address");

			boolean isExist = false;// 判断是否存在该用户

			if (!username.equals("") && !password.equals("")) {
				isExist = service.register(username, password, name, sex, tel, address);
				if (isExist == true) {
					resp.getWriter().write("该用户已经注册，请直接");
					resp.getWriter().write("<a href='" + req.getContextPath() + "/client/ClientServlet?op=category'>登录</a>");
				} else {
					resp.getWriter().write("注册成功!");
					resp.getWriter().write("2s后跳往登录页");
					resp.setHeader("Refresh", "2;URL=" + req.getContextPath() + "/client/ClientServlet?op=category");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCategoryBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = service.getCategoryBook(req.getParameter("cid"));// 文学艺术类选课
		req.setAttribute("books", books);
		List<Category> categoryList= managerService.findAllCategory();
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void personInformation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");

		service.personInformation(username, name, sex, tel, address);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功！</div>");
	}

	private void personPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");

		service.personPassword(password, username);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/duigou.png'/>修改成功！</div>");
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("search");
		List<Book> searchmessage = service.search(search);
		req.setAttribute("books", searchmessage);
		req.getRequestDispatcher("/showBook.jsp").forward(req, resp);
	}

	private void particulars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/particulars.jsp").forward(req, resp);
	}

	private void video(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/video.jsp").forward(req, resp);
	}

	// 通过选课id找到选课信息
	private Book findBookById(String book_id) {
		Book book = service.findBookById(book_id);
		return book;
	}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);

		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addBook(book);
	}

	private void delItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String book_id = req.getParameter("book_id");
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		cart.getItmes().remove(book_id);
		resp.sendRedirect(req.getContextPath() + "/showCart.jsp");
	}

	private void buNow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String book_id = req.getParameter("book_id");
		Book book = findBookById(book_id);
		HttpSession session = req.getSession();
		Cart cart = new Cart();
		session.setAttribute("buyNowBook", cart);
		cart.addBook(book);
		resp.sendRedirect(req.getContextPath() + "/buyNow.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
