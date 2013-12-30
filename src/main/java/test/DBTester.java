package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.rest.domain.ImageFile;
import cn.rest.service.ImageService;

public class DBTester {

	@Test
	public void testDB() {

		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"D:/test/test1/springREST/springREST/src/main/webapp/WEB-INF/spring/root-context.xml");

		ImageService is = ac.getBean(ImageService.class);

		ImageFile i = new ImageFile();

		i.setName("2.jpg");

		is.saveImage(i);

	}

}
