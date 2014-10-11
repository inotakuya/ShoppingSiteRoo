// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import jp.com.inotaku.domain.Item;
import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.domain.SaleDetail;
import jp.com.inotaku.service.ItemService;
import jp.com.inotaku.service.SaleDetailService;
import jp.com.inotaku.service.SaleService;
import jp.com.inotaku.web.SaleDetailController;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect SaleDetailController_Roo_Controller {
    
    @Autowired
    SaleDetailService SaleDetailController.saleDetailService;
    
    @Autowired
    ItemService SaleDetailController.itemService;
    
    @Autowired
    SaleService SaleDetailController.saleService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SaleDetailController.create(@Valid SaleDetail saleDetail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, saleDetail);
            return "saledetails/create";
        }
        uiModel.asMap().clear();
        saleDetailService.saveSaleDetail(saleDetail);
        return "redirect:/saledetails/" + encodeUrlPathSegment(saleDetail.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SaleDetailController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SaleDetail());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (saleService.countAllSales() == 0) {
            dependencies.add(new String[] { "sale", "sales" });
        }
        if (itemService.countAllItems() == 0) {
            dependencies.add(new String[] { "item", "items" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "saledetails/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SaleDetailController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("saledetail", saleDetailService.findSaleDetail(id));
        uiModel.addAttribute("itemId", id);
        return "saledetails/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SaleDetailController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("saledetails", SaleDetail.findSaleDetailEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) saleDetailService.countAllSaleDetails() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("saledetails", SaleDetail.findAllSaleDetails(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "saledetails/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SaleDetailController.update(@Valid SaleDetail saleDetail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, saleDetail);
            return "saledetails/update";
        }
        uiModel.asMap().clear();
        saleDetailService.updateSaleDetail(saleDetail);
        return "redirect:/saledetails/" + encodeUrlPathSegment(saleDetail.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SaleDetailController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, saleDetailService.findSaleDetail(id));
        return "saledetails/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SaleDetailController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SaleDetail saleDetail = saleDetailService.findSaleDetail(id);
        saleDetailService.deleteSaleDetail(saleDetail);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/saledetails";
    }
    
    void SaleDetailController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("saleDetail_updatedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void SaleDetailController.populateEditForm(Model uiModel, SaleDetail saleDetail) {
        uiModel.addAttribute("saleDetail", saleDetail);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("items", itemService.findAllItems());
        uiModel.addAttribute("sales", saleService.findAllSales());
    }
    
    String SaleDetailController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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