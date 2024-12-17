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
}
