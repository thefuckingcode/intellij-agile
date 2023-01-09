package com.github.thefuckingcode.choerodonplugin.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public final class JsonHelper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonHelper() {
    }

    static {
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 通过jackson反序列化列表
     *
     * @param value       json
     * @param elementType 元素类型
     * @param <T>         泛型
     * @return 列表
     */
    public static <T> List<T> unmarshallList(String value, Class<T> elementType) {
        try {
            return OBJECT_MAPPER.readValue(value, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, elementType));
        } catch (IOException e) {
            throw new RuntimeException("Failed to unmarshal by jackson. It's unexpected and may be an internal error. The json is: " + value, e);
        }
    }

    /**
     * 通过jackson反序列化对象
     *
     * @param json json内容
     * @param type 类型
     * @param <T>  泛型
     * @return 对象
     */
    public static <T> T unmarshalByJackson(String json, Class<T> type) {
        if (json.length() == 0) {
            throw new RuntimeException("JSON to be unmarshalled should not be empty");
        }
        if (type == null) {
            throw new RuntimeException("Type should not be null");
        }
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to unmarshal by jackson. It's unexpected and may be an internal error. The json is: " + json, e);
        }
    }

    public static <T> T unmarshalByJackson(String json, TypeReference<T> typeReference) {
        if (json.length() == 0) {
            throw new RuntimeException("JSON to be unmarshalled should not be empty");
        }
        if (typeReference == null) {
            throw new RuntimeException("typeReference should not be null");
        }
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to unmarshal by jackson. It's unexpected and may be an internal error. The json is: " + json, e);
        }
    }

    /**
     * 通过jackson序列化对象
     *
     * @param object 非空对象
     * @return JSON字符串
     */
    public static String marshalByJackson(Object object) {
        if (object == null) {
            throw new RuntimeException("Object to be marshaled should not be null");
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to marshal by jackson. It's unexpected and may be an internal error. The object is: " + object.toString(), e);
        }
    }

    /**
     * 将json中的双引号替换为单引号 (不管属性值包括双引号的情况)
     *
     * @param json json
     * @return 单引号的json
     */
    public static String singleQuoteWrapped(String json) {
        return json.replaceAll("\"", "'");
    }


    public static JsonNode unmarshallAsNode(String json) throws IOException {
        return OBJECT_MAPPER.readTree(json);
    }

    /**
     * 将JSON节点转为目标类型的对象
     *
     * @param node        JSON节点
     * @param targetClass 目标类型
     * @param <T>         目标类型
     * @return 目标对象
     */
    public static <T> T convertNode(TreeNode node, Class<T> targetClass) {
        try {
            return OBJECT_MAPPER.treeToValue(node, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to unmarshal by jackson. It's unexpected and may be an internal error. The jsonNode is: " + node, e);
        }
    }

    /**
     * 将JSON节点转为目标类型的对象
     *
     * @param node          JSON节点
     * @param typeReference 目标类型
     * @param <T>           目标类型
     * @return 目标对象
     */
    public static <T> T convertNode(TreeNode node, TypeReference<T> typeReference) {
        return OBJECT_MAPPER.convertValue(node, typeReference);
    }
}
