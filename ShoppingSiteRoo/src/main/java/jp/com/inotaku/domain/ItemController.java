package jp.com.inotaku.domain;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/items")
@Controller
@RooWebScaffold(path = "items", formBackingObject = Item.class)
@RooWebFinder
public class ItemController {
}
