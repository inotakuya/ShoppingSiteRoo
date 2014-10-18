package jp.com.inotaku.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.service.ConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/consumers")
@Controller
@RooWebScaffold(path = "consumers", formBackingObject = Consumer.class)
public class ConsumerController {
	
	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Consumer consumer, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, consumer);
			return "consumers/create";
		}
		uiModel.asMap().clear();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordHash = encoder.encode(consumer.getPassword());
		consumer.setPassword(passwordHash);
		consumerService.saveConsumer(consumer);
		return "redirect:/consumers/"
				+ encodeUrlPathSegment(consumer.getId().toString(),
						httpServletRequest);
	}
	
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Consumer consumer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, consumer);
            return "consumers/update";
        }
        uiModel.asMap().clear();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(consumer.getPassword());
        consumer.setPassword(passwordHash);
        consumerService.updateConsumer(consumer);
        return "redirect:/consumers/" + encodeUrlPathSegment(consumer.getId().toString(), httpServletRequest);
    }
}
