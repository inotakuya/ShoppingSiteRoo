package jp.com.inotaku.controller;
import jp.com.inotaku.domain.Consumer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/consumers")
@Controller
@RooWebScaffold(path = "consumers", formBackingObject = Consumer.class)
public class ConsumerController {
}
