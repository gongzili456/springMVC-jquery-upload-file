package cn.rest.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.rest.domain.ImageFile;
import cn.rest.service.ImageService;
import cn.rest.utils.PropertyHelper;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	ImageService imageService;

	private String path = PropertyHelper
			.getProperties(PropertyHelper.UPLOAD_PROPERTIES);

	private final char SEPARATORCHAR = File.separatorChar;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public List<ImageFile> upload(MultipartHttpServletRequest request) {

		List<ImageFile> list = new LinkedList<ImageFile>();

		Iterator<String> names = request.getFileNames();
		while (names.hasNext()) {
			MultipartFile mf = request.getFile(names.next());
			if (mf.isEmpty()) {
				continue;
			}
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(mf.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String type = mf.getContentType();
			long size = mf.getSize();

			int height = bi.getHeight();
			int width = bi.getWidth();
			long current = System.currentTimeMillis();
			Date d = new Date(current);
			SimpleDateFormat format = new SimpleDateFormat("yyyy"
					+ SEPARATORCHAR + "MM" + SEPARATORCHAR + "dd");
			String datePath = format.format(d);
			File dir = new File(path, datePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ImageFile imageFile = new ImageFile();
			String end = type.substring(type.indexOf("/") + 1);
			imageFile.setName(UUID.randomUUID().toString() + "." + end);
			imageFile.setContentType(type);
			imageFile.setSize(size);
			imageFile.setDate(current);
			imageFile.setHeight(height);
			imageFile.setWidth(width);
			imageFile.setRatio((double) width / (double) height);
			File f = new File(dir, imageFile.getName());
			try {
				FileUtils.copyInputStreamToFile(mf.getInputStream(), f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			imageFile.setPath(f.getAbsolutePath());
			imageFile.setUrl(datePath + SEPARATORCHAR + imageFile.getName());
			list.add(imageFile);
		}
		imageService.saveImageBatch(list);
		return list;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable String name) {
		imageService.deleteImage(name);
	}
}
