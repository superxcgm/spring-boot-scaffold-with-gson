package com.cmb.lf65.ams.support;

import com.cmb.lf65.ams.infrastructure.gson.AmsPageResourcesTypeAdapter;
import com.cmb.lf65.ams.infrastructure.gson.AmsResourceTypeAdapter;
import com.cmb.lf65.ams.infrastructure.gson.AmsResourcesTypeAdapter;
import com.cmb.lf65.ams.infrastructure.gson.LinksTypeAdapter;
import com.cmb.lf65.ams.rest.AmsPageResources;
import com.cmb.lf65.ams.rest.AmsResource;
import com.cmb.lf65.ams.rest.AmsResources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Type;
import java.util.List;

import static java.util.Collections.singletonList;

final class GsonConfigurationForUnitTest {

    private GsonConfigurationForUnitTest() {
    }

    static GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson());
        gsonHttpMessageConverter.setSupportedMediaTypes(singletonList(MediaType.APPLICATION_JSON));
        return gsonHttpMessageConverter;
    }

    private static Gson gson() {
        final Type linkListType = new TypeToken<List<Link>>() {
        }.getType();

        return new GsonBuilder()
                .registerTypeAdapter(linkListType, new LinksTypeAdapter())
                .registerTypeAdapter(AmsResource.class, new AmsResourceTypeAdapter<>())
                .registerTypeAdapter(AmsResources.class, new AmsResourcesTypeAdapter<>())
                .registerTypeAdapter(AmsPageResources.class, new AmsPageResourcesTypeAdapter<>())
                .create();
    }
}
