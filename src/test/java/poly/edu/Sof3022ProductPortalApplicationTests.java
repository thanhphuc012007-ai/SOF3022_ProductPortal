package poly.edu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Sof3022ProductPortalApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testIndexPage() throws Exception {
		mockMvc.perform(get("/home/index"))
				.andExpect(status().isOk())
				.andExpect(view().name("home/index"))
				.andExpect(model().attribute("appName", "Product Portal"))
				.andExpect(model().attribute("subject", "SOF3022 - Spring Boot MVC"))
				.andExpect(model().attribute("semester", "Summer 2026"))
				.andExpect(model().attribute("message", "Chào mừng bạn đến với hệ thống quản lý sản phẩm"));
	}

	@Test
	void testAboutPage() throws Exception {
		mockMvc.perform(get("/home/about"))
				.andExpect(status().isOk())
				.andExpect(view().name("home/about"))
				.andExpect(model().attribute("appName", "Product Portal"));
	}

	@Test
	void testHelpPage() throws Exception {
		mockMvc.perform(get("/home/help"))
				.andExpect(status().isOk())
				.andExpect(view().name("home/help"))
				.andExpect(model().attribute("appName", "Product Portal"));
	}

	@Test
	void testRootRedirect() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/home/index"));
	}
}
