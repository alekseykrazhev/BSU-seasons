import requests
import json


def get_items():
    req = requests.get('http://localhost:5101/api/v1/Catalog/items')
    return req.json()


def get_item(item_id):
    req = requests.get(f'http://localhost:5101/catalog-api/api/v1/Catalog/items/{item_id}')
    return req.json()


def create_new_item(id, name, descr, price, pic, picURI, catID, cat, brandID, brand, avil, rest, max):
    data = {'id': id, 'name': name, 'description': descr, 'price': price, 'pictureFileName': pic, "pictureUri": picURI,
            "catalogTypeId": catID, "catalogType": {"id": 0, "brand": "string"}, "catalogBrandId": brandID, "catalogBrand": {"id": 0, "brand": "string"},
            "availableStock": avil, "restockThreshold": rest, "maxStockThreshold": max, "onReorder": False}

    # data_json = json.dumps(data)
    print(data)

    api = requests.post('http://localhost:5101/catalog-api/api/v1/Catalog/items', data=data)
    api.raise_for_status()
    return api.status_code


if __name__ == '__main__':
    print(get_items())
    print(get_item(2))
    print(create_new_item(100, 'name', 'descr.', 39.5, '2.png', 'http://host.docker.internal:5202/c/api/v1/catalog'
                                                                '/items' '/2/pic/', 2, None, 5, None, 100, 0, 0))
    print(get_items())
