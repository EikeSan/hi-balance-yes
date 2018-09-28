# hi-balance-yes

### Auth
- POST /oauth/token
```json
{
	"grant_type": "password",
	"client_id": "my-trusted-client",
	"client_secret": "secret",
	"username": "user",
	"password": "user"
}
```
### User
- GET /users
- POST /users/register
```json
{
    "username": "user",
    "password": "123456",
    "roles": [
        {
          "name": "USER"
        },
        {
          "name": "ACTUATOR"
        }
    ],
    "active": true
}
```

### User Account
- POST /users/accounts
```json
{
	"name": "wallet",
	"type": "wallet",
	"bank": "citibank"
}
```

### User Card
- POST /users/cards
```json
    {
    	"name": "NuBank",
    	"account":{
    	  "id": 1
    	}
    }
```
### User Balance

- GET /users/balances
- POST /users/balances/fixedIncome
```json
{
  "value": 1000.0,
  "transaction": {
    "category" : "salary"
  },
  "account" : {
    "id" : 1
  }
}
```
