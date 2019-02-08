## Customer service
- all APIs need private key
- service exposes 5 APIs examples below
- all events againts aggreagte are stored are in event store
- the current state of the customer is stored in JPA store
- both data storages are backed by H2 and can be changed to another storage provider with configuration chages


### design choice
- Axon with spring
- CQRS everything is either a command or a query and never both
- event driven ensuring loose coupling in application, on attaching event bus shared across application instances
it will ease horizontal scaling of application


### creating customer

#### request
```text
curl -X POST \
  http://localhost:8083/create \
  -H 'Content-Type: application/json' \
  -H 'X-API-Key: abcdef12345' \
  -d '{
	"points":100
}'
```
#### response
```text
0f93221c-adb5-4705-8033-22abe248e590
```
### add points

#### request
```text
curl -X PUT \
  http://localhost:8083/points/add \
  -H 'Content-Type: application/json' \
  -H 'X-API-Key: abcdef12345' \
  -d '{
	"id":"0f93221c-adb5-4705-8033-22abe248e590",
	"points":10
}'
```
### deduct points
#### request
```text
curl -X PUT \
  http://localhost:8083/points/deduct \
  -H 'Content-Type: application/json' \
  -H 'X-API-Key: abcdef12345' \
  -d '{
	"id":"0f93221c-adb5-4705-8033-22abe248e590",
	"points":10
}'
```

### requesting all changes to customer

#### request
```text
curl -X GET \
  http://localhost:8083/events/0f93221c-adb5-4705-8033-22abe248e590 \
  -H 'X-API-Key: abcdef12345'
```

#### response
```text
[{
	"id": "0f93221c-adb5-4705-8033-22abe248e590",
	"points": 100,
	"message": "initialize customer points",
	"creationTime": "2019-02-07T11:45:10.447Z"
}, {
	"id": "0f93221c-adb5-4705-8033-22abe248e590",
	"points": 10,
	"message": "add points",
	"creationTime": "2019-02-07T11:45:45.214Z"
}, {
	"id": "0f93221c-adb5-4705-8033-22abe248e590",
	"points": 10,
	"message": "deducted points",
	"creationTime": "2019-02-07T11:45:53.583Z"
}]
```

### requesting current balance of customer points

#### request
```text
curl -X GET \
  http://localhost:8083/points/0f93221c-adb5-4705-8033-22abe248e590 \
  -H 'Content-Type: application/json' \
  -H 'X-API-Key: abcdef12345' \
  -d '{
	"id":"264372c6-4c4b-4151-9d90-f4796d0a5e33",
	"points":10
}'
```
#### response
```text
100
```
