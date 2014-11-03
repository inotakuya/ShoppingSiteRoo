// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.web.ConsumerController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ConsumerController_Roo_Controller {
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ConsumerController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Consumer());
        return "consumers/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ConsumerController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("consumer", consumerService.findConsumer(id));
        uiModel.addAttribute("itemId", id);
        return "consumers/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ConsumerController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("consumers", consumerService.findConsumerEntries(firstResult, sizeNo));
            float nrOfPages = (float) consumerService.countAllConsumers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("consumers", consumerService.findAllConsumers());
        }
        return "consumers/list";
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ConsumerController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, consumerService.findConsumer(id));
        return "consumers/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ConsumerController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Consumer consumer = consumerService.findConsumer(id);
        consumerService.deleteConsumer(consumer);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/consumers";
    }
    
    void ConsumerController.populateEditForm(Model uiModel, Consumer consumer) {
        uiModel.addAttribute("consumer", consumer);
    }
    
    String ConsumerController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
