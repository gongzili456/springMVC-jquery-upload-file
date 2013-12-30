package cn.rest.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rest.dao.ImageDao;
import cn.rest.domain.ImageFile;

@Component
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDao imageDao;

	@Override
	public boolean saveImage(ImageFile i) {
		return imageDao.saveImage(i);
	}

	@Override
	public boolean deleteImage(String name) {
		ImageFile i = imageDao.queryImage(name);
		File f = new File(i.getPath());
		boolean de = f.delete();
		if (de) {
			return imageDao.deleteImage(name);
		}
		return false;
	}

	@Override
	public ImageFile queryImage(String name) {
		return imageDao.queryImage(name);
	}

	@Override
	public boolean saveImageBatch(List<ImageFile> list) {
		return imageDao.saveImageBatch(list);
	}

}
