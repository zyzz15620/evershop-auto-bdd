package data;

public class ProductsData {
    public static final String PRODUCT_1 = """
            {
                  "name": "Bitis",
                  "product_id": "",
                  "sku": "123",
                  "price": "200",
                  "weight": "1",
                  "tax_class": null,
                  "description": "[{\\"id\\":\\"r__4a6b8415-ffc2-4af2-abd7-f18851d138d5\\",\\"size\\":1,\\"columns\\":[{\\"id\\":\\"c__13572cc7-c04d-49e8-8498-31879c630df0\\",\\"size\\":1,\\"data\\":{\\"time\\":1734167801497,\\"blocks\\":[{\\"id\\":\\"HbeU572X-t\\",\\"type\\":\\"paragraph\\",\\"data\\":{\\"text\\":\\"meow meow meow this is desc\\"}}],\\"version\\":\\"2.30.5\\"}}]}]",
                  "url_key": "bitis_mandala",
                  "meta_title": "Bitis Mandala",
                  "meta_keywords": "Bitis",
                  "meta_description": "meow",
                  "status": "1",
                  "visibility": "1",
                  "manage_stock": "1",
                  "stock_availability": "1",
                  "qty": "100",
                  "group_id": "1",
                  "attributes": [
                    {
                      "attribute_code": "color"
                    },
                    {
                      "attribute_code": "size"
                    }
                  ]
                }
        """;

    public static final String PRODUCT_WITH_IMAGE = """
            {
              "name": "Backpack",
              "product_id": "",
              "sku": "111",
              "price": "300",
              "weight": "1",
              "tax_class": null,
              "description": "[]",
              "images": [
                "/assets/catalog/7902/3063/mandala.jpg"
              ],
              "url_key": "1",
              "meta_title": "",
              "meta_keywords": "",
              "meta_description": "",
              "status": "1",
              "visibility": "1",
              "manage_stock": "1",
              "stock_availability": "1",
              "qty": "5",
              "group_id": "1",
              "attributes": [
                {
                  "attribute_code": "color"
                },
                {
                  "attribute_code": "size"
                }
              ]
            }
            """;

    public static final String STUB_RESPONSE_CREATE_PRODUCT = """
            {
                "data": {
                    "product_description_id": 65,
                    "product_description_product_id": 73,
                    "name": "1",
                    "description": "[]",
                    "short_description": null,
                    "url_key": "1",
                    "meta_title": "",
                    "meta_description": "",
                    "meta_keywords": "",
                    "insertId": 73,
                    "product_id": 73,
                    "uuid": "ca65bc6a-4295-4738-8509-c1ac11f40100",
                    "type": "simple",
                    "variant_group_id": null,
                    "visibility": true,
                    "group_id": 1,
                    "sku": "1",
                    "price": "1.0000",
                    "weight": "1.0000",
                    "tax_class": null,
                    "status": true,
                    "created_at": "2024-12-17T08:18:45.320Z",
                    "updated_at": "2024-12-17T08:18:45.320Z",
                    "category_id": null,
                    "links": [
                        {
                            "rel": "productGrid",
                            "href": "/admin/products",
                            "action": "GET",
                            "types": [
                                "text/xml"
                            ]
                        },
                        {
                            "rel": "view",
                            "href": "/product/ca65bc6a-4295-4738-8509-c1ac11f401e9",
                            "action": "GET",
                            "types": [
                                "text/xml"
                            ]
                        },
                        {
                            "rel": "edit",
                            "href": "/admin/products/edit/ca65bc6a-4295-4738-8509-c1ac11f401e9",
                            "action": "GET",
                            "types": [
                                "text/xml"
                            ]
                        }
                    ]
                }
            }
            """;
}
