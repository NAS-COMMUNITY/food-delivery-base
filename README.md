## Step
- user can signup and login and 

## Product 
``` json
{
    "id": "34e67c1d-b559-4ee0-bd58-fc3010923fa6",
    "createdAt": "2022-05-25T14:46:09.544541",
    "updatedAt": "2022-05-25T14:48:29.775933",
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
    "productDtoSet": null,
    "status": "PENDING",
    "price": 500.0,
    "rejectReason": null
}
```
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