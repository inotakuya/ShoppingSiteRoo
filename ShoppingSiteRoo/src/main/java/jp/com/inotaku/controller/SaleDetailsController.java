package jp.com.inotaku.controller;
import jp.com.inotaku.domain.SaleDetails;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/saledetailses")
@Controller
@RooWebScaffold(path = "saledetailses", formBackingObject = SaleDetails.class)
public class SaleDetailsController {
}
