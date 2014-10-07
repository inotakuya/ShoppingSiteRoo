package jp.com.inotaku.controller;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import jp.com.inotaku.domain.Item;
import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.domain.SaleDetails;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/saledetailses")
@Controller
@RooWebScaffold(path = "saledetailses", formBackingObject = SaleDetails.class)
public class SaleDetailsController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid SaleDetails saleDetails, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, saleDetails);
            return "saledetailses/create";
        }
        uiModel.asMap().clear();
        saleDetails.persist();
        return "redirect:/saledetailses/" + encodeUrlPathSegment(saleDetails.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new SaleDetails());
        return "saledetailses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("saledetails", SaleDetails.findSaleDetails(id));
        uiModel.addAttribute("itemId", id);
        return "saledetailses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("saledetailses", SaleDetails.findSaleDetailsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) SaleDetails.countSaleDetailses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("saledetailses", SaleDetails.findAllSaleDetailses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "saledetailses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid SaleDetails saleDetails, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, saleDetails);
            return "saledetailses/update";
        }
        uiModel.asMap().clear();
        saleDetails.merge();
        return "redirect:/saledetailses/" + encodeUrlPathSegment(saleDetails.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, SaleDetails.findSaleDetails(id));
        return "saledetailses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SaleDetails saleDetails = SaleDetails.findSaleDetails(id);
        saleDetails.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/saledetailses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("saleDetails_updatedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, SaleDetails saleDetails) {
        uiModel.addAttribute("saleDetails", saleDetails);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("items", Item.findAllItems());
        uiModel.addAttribute("sales", Sale.findAllSales());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
