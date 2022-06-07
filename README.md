## Step
- user can signup and login and 


![](./screen/product.png)


![](./screen/order.png)
## Order
``` json
{
            "id": "34e67c1d-b559-4ee0-bd58-fc3010923fa6",
            "createdAt": "2022-05-25T14:46:09.544541",
            "updatedAt": "2022-06-07T14:16:14.490966",
            "customer": {
                "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                "firstName": "xhub",
                "lastName": "xhub",
                "email": "admin.abbal10@gmail.com",
                "password": "anas123",
                "role": "ADMIN",
                "createdAt": "2022-05-25T14:44:38.259475",
                "updatedAt": "2022-05-25T14:44:38.259475"
            },
            "billingAddress": {
                "id": "9039dcc9-365e-41ab-b26a-f722f82fdd5f",
                "street": "oujda",
                "city": "Oujda",
                "country": "Maroc",
                "customer": {
                    "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                    "firstName": "xhub",
                    "lastName": "xhub",
                    "email": "admin.abbal10@gmail.com",
                    "password": "anas123",
                    "role": "ADMIN",
                    "createdAt": "2022-05-25T14:44:38.259475",
                    "updatedAt": "2022-05-25T14:44:38.259475"
                },
                "createdAt": "2022-05-25T14:45:13.739097",
                "updatedAt": "2022-05-25T14:45:13.739097"
            },
            "shippingAddress": {
                "id": "9039dcc9-365e-41ab-b26a-f722f82fdd5f",
                "street": "oujda",
                "city": "Oujda",
                "country": "Maroc",
                "customer": {
                    "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                    "firstName": "xhub",
                    "lastName": "xhub",
                    "email": "admin.abbal10@gmail.com",
                    "password": "anas123",
                    "role": "ADMIN",
                    "createdAt": "2022-05-25T14:44:38.259475",
                    "updatedAt": "2022-05-25T14:44:38.259475"
                },
                "createdAt": "2022-05-25T14:45:13.739097",
                "updatedAt": "2022-05-25T14:45:13.739097"
            },
            "products": [
                {
                    "type": "TACOS",
                    "price": 500.00
                },
                {
                    "type": "TACOS",
                    "price": 500.00
                }
            ],
            "status": "PENDING",
            "price": 1000.0,
            "rejectReason": null
        }
```
![](./screen/customer.png)
## Customer
``` json
{
    "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
    "firstName": "xhub",
    "lastName": "xhub",
    "email": "admin.abbal10@gmail.com",
    "password": "anas123",
    "role": "ADMIN",
    "createdAt": "2022-05-25T14:44:38.259475",
    "updatedAt": "2022-05-25T14:44:38.259475"
}
```
![](./screen/address.png)
## Address
``` json
{
            "id": "9039dcc9-365e-41ab-b26a-f722f82fdd5f",
            "street": "oujda",
            "city": "Oujda",
            "country": "Maroc",
            "customer": {
                "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                "firstName": "xhub",
                "lastName": "xhub",
                "email": "admin.abbal10@gmail.com",
                "password": "anas123",
                "role": "ADMIN",
                "createdAt": "2022-05-25T14:44:38.259475",
                "updatedAt": "2022-05-25T14:44:38.259475"
            },
            "createdAt": "2022-05-25T14:45:13.739097",
            "updatedAt": "2022-05-25T14:45:13.739097"
        }
```
## Test Signup
![](./screen/signup.png)

## Test Login
![](./screen/login.png)