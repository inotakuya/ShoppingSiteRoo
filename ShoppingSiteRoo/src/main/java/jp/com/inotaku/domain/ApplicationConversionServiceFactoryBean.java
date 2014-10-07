package jp.com.inotaku.domain;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	public Converter<Consumer, String> getConsumerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<jp.com.inotaku.domain.Consumer, java.lang.String>() {
            public String convert(Consumer consumer) {
                return new StringBuilder().append(consumer.getConsumerName()).append(' ').append(consumer.getPassword()).append(' ').append(consumer.getAddress()).append(' ').append(consumer.getEmail()).toString();
            }
        };
    }

	public Converter<Long, Consumer> getIdToConsumerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, jp.com.inotaku.domain.Consumer>() {
            public jp.com.inotaku.domain.Consumer convert(java.lang.Long id) {
                return Consumer.findConsumer(id);
            }
        };
    }

	public Converter<String, Consumer> getStringToConsumerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, jp.com.inotaku.domain.Consumer>() {
            public jp.com.inotaku.domain.Consumer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Consumer.class);
            }
        };
    }

	public Converter<Item, String> getItemToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<jp.com.inotaku.domain.Item, java.lang.String>() {
            public String convert(Item item) {
                return new StringBuilder().append(item.getItemId()).append(' ').append(item.getItemName()).append(' ').append(item.getPrice()).append(' ').append(item.getDescription()).toString();
            }
        };
    }

	public Converter<Long, Item> getIdToItemConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, jp.com.inotaku.domain.Item>() {
            public jp.com.inotaku.domain.Item convert(java.lang.Long id) {
                return Item.findItem(id);
            }
        };
    }

	public Converter<String, Item> getStringToItemConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, jp.com.inotaku.domain.Item>() {
            public jp.com.inotaku.domain.Item convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Item.class);
            }
        };
    }

	public Converter<Sale, String> getSaleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<jp.com.inotaku.domain.Sale, java.lang.String>() {
            public String convert(Sale sale) {
                return new StringBuilder().append(sale.getSaleId()).append(' ').append(sale.getUpdateDate()).append(' ').append(sale.getTotalAmount()).toString();
            }
        };
    }

	public Converter<Long, Sale> getIdToSaleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, jp.com.inotaku.domain.Sale>() {
            public jp.com.inotaku.domain.Sale convert(java.lang.Long id) {
                return Sale.findSale(id);
            }
        };
    }

	public Converter<String, Sale> getStringToSaleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, jp.com.inotaku.domain.Sale>() {
            public jp.com.inotaku.domain.Sale convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Sale.class);
            }
        };
    }

	public Converter<SaleDetails, String> getSaleDetailsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<jp.com.inotaku.domain.SaleDetails, java.lang.String>() {
            public String convert(SaleDetails saleDetails) {
                return new StringBuilder().append(saleDetails.getSaleDetailsId()).append(' ').append(saleDetails.getUpdateDate()).append(' ').append(saleDetails.getQuantity()).toString();
            }
        };
    }

	public Converter<Long, SaleDetails> getIdToSaleDetailsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, jp.com.inotaku.domain.SaleDetails>() {
            public jp.com.inotaku.domain.SaleDetails convert(java.lang.Long id) {
                return SaleDetails.findSaleDetails(id);
            }
        };
    }

	public Converter<String, SaleDetails> getStringToSaleDetailsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, jp.com.inotaku.domain.SaleDetails>() {
            public jp.com.inotaku.domain.SaleDetails convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), SaleDetails.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getConsumerToStringConverter());
        registry.addConverter(getIdToConsumerConverter());
        registry.addConverter(getStringToConsumerConverter());
        registry.addConverter(getItemToStringConverter());
        registry.addConverter(getIdToItemConverter());
        registry.addConverter(getStringToItemConverter());
        registry.addConverter(getSaleToStringConverter());
        registry.addConverter(getIdToSaleConverter());
        registry.addConverter(getStringToSaleConverter());
        registry.addConverter(getSaleDetailsToStringConverter());
        registry.addConverter(getIdToSaleDetailsConverter());
        registry.addConverter(getStringToSaleDetailsConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
