package jp.com.inotaku.web;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import jp.com.inotaku.domain.Item;

import org.apache.commons.io.IOUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@RequestMapping("/items")
@Controller
@RooWebScaffold(path = "items", formBackingObject = Item.class)
public class ItemController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value = "/{id}/image", method = RequestMethod.GET)
	public void showImage(@PathVariable long id, HttpServletResponse response) {
		Item item = itemService.findItem(id);
		if (item != null) {
			byte[] image = item.getImage();
			if (image != null) {
				try {
					OutputStream stream = response.getOutputStream();
					IOUtils.copy(new ByteArrayInputStream(image), stream);
					stream.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "sortFieldName", required = false) String sortFieldName,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("items", Item.findItemEntries(firstResult,
					sizeNo, sortFieldName, sortOrder));
			float nrOfPages = (float) itemService.countAllItems() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("items",
					Item.findAllItems(sortFieldName, sortOrder));
		}
		return "items/index2";
		/*return "items/list";*/
	}
}
