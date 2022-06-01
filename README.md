## Step
- user can signup and login and 


![](./screen/product.png)

## Product 
``` json
{
            "id": "53d2f301-b953-46be-bcdf-b1df6d6773ca",
            "createdAt": "2022-05-25T14:47:33.011746",
            "updatedAt": "2022-05-25T14:47:33.011746",
            "deleted": false,
            "type": "TACOS",
            "order": {
                "id": "34e67c1d-b559-4ee0-bd58-fc3010923fa6",
                "createdAt": "2022-05-25T14:46:09.544541",
                "updatedAt": "2022-05-25T14:48:29.775933",
                "deleted": false,
                "customer": {
                    "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                    "createdAt": "2022-05-25T14:44:38.259475",
                    "updatedAt": "2022-05-25T14:44:38.259475",
                    "deleted": false,
                    "firstName": "xhub",
                    "lastName": "xhub",
                    "email": "admin.abbal10@gmail.com",
                    "password": "anas123",
                    "role": "ADMIN"
                },
                "billingAddress": {
                    "id": "9039dcc9-365e-41ab-b26a-f722f82fdd5f",
                    "createdAt": "2022-05-25T14:45:13.739097",
                    "updatedAt": "2022-05-25T14:45:13.739097",
                    "deleted": false,
                    "street": "oujda",
                    "city": "Oujda",
                    "country": "Maroc",
                    "customer": {
                        "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                        "createdAt": "2022-05-25T14:44:38.259475",
                        "updatedAt": "2022-05-25T14:44:38.259475",
                        "deleted": false,
                        "firstName": "xhub",
                        "lastName": "xhub",
                        "email": "admin.abbal10@gmail.com",
                        "password": "anas123",
                        "role": "ADMIN"
                    }
                },
                "shippingAddress": {
                    "id": "9039dcc9-365e-41ab-b26a-f722f82fdd5f",
                    "createdAt": "2022-05-25T14:45:13.739097",
                    "updatedAt": "2022-05-25T14:45:13.739097",
                    "deleted": false,
                    "street": "oujda",
                    "city": "Oujda",
                    "country": "Maroc",
                    "customer": {
                        "id": "688e8595-25f4-4415-b100-81c5bf50b0f2",
                        "createdAt": "2022-05-25T14:44:38.259475",
                        "updatedAt": "2022-05-25T14:44:38.259475",
                        "deleted": false,
                        "firstName": "xhub",
                        "lastName": "xhub",
                        "email": "admin.abbal10@gmail.com",
                        "password": "anas123",
                        "role": "ADMIN"
                    }
                },
                "status": "PENDING",
                "price": 500.0,
                "rejectReason": null
            }
        }
```
![](./screen/order.png)
## Order
``` json
{
    "id": "ffe47e0b-7239-44c7-be73-e54eb9cd59f0",
    "customer": {
        "id": "14e1da36-1513-4ac1-ae25-cc217da1621d",
        "firstName": "",
        "lastName": "",
        "email": "",
    },
    "billingAddress": {
        "id": "7f0a292a-65f2-428d-a3db-b4c91d48b0ac",
        "street": "22 rue limoun",
        "city": "Oujda",
        "country": "Maroc",
        "customer": {
            "id": "14e1da36-1513-4ac1-ae25-cc217da1621d",
            "firstName": "",
            "lastName": "",
            "email": "",
        }
    },
    "shippingAddress": {
        "id": "7f0a292a-65f2-428d-a3db-b4c91d48b0ac",
        "street": "22 rue limoun",
        "city": "Oujda",
        "country": "Maroc",
        "customer": {
            "id": "14e1da36-1513-4ac1-ae25-cc217da1621d",
            "firstName": "",
            "lastName": "",
            "email": "",
        }
    },
    "type": "PIZZA",
    "status": "PENDING",
    "price": 1231241.1231209701,
    "rejectReason": ""
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