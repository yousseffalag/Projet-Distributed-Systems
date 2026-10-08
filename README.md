# Projet 1 : E-Bank Microservices Architecture (Partie 1)

Ce projet implémente la **Partie 1** de l'architecture distribuée pour une application bancaire (E-Bank) basée sur **Spring Cloud**, **OpenFeign**, **Resilience4j**, **Eureka Discovery** et **Spring Cloud Gateway**.

Développé par : **Falag Youssef** (`com.falag.youssef`)

---

## 🏛️ Architecture et Microservices

| Service | Port | Description |
| :--- | :--- | :--- |
| **`discovery-service`** | `8761` | Serveur d'enregistrement Netflix Eureka |
| **`customer-service`** | `8056` | Gestion des clients (JPA, H2, REST, Swagger) |
| **`ebank-service`** | `8057` | Gestion des comptes bancaires, client OpenFeign & Circuit Breaker |
| **`gateway-service`** | `9999` | API Gateway avec routage dynamique vers les microservices |

---

## 🚀 Ordre de Démarrage des Services

Pour assurer le bon enregistrement et la communication inter-services, démarrez les applications dans l'ordre suivant :
1. **`discovery-service`** (`DiscoveryServiceApplication`) - Attendre que le tableau de bord Eureka soit accessible sur `http://localhost:8761`.
2. **`customer-service`** (`CustomerServiceApplication`) - Le service s'enregistre auprès d'Eureka.
3. **`ebank-service`** (`EbankServiceApplication`) - Le service s'enregistre et communique avec `customer-service` via OpenFeign.
4. **`gateway-service`** (`GatewayServiceApplication`) - La passerelle découvre dynamiquement les routes.

---

## 🧪 Documentation et Tests des APIs

### 1. Accès direct aux Microservices
- **Customer Service** :
  - Liste des clients : `GET http://localhost:8056/customers`
  - Détail client : `GET http://localhost:8056/customers/1`
  - Swagger UI : `http://localhost:8056/swagger-ui.html`
  - Console H2 : `http://localhost:8056/h2-console` (JDBC URL : `jdbc:h2:mem:customers-db`)
- **E-Bank Service** :
  - Liste des comptes : `GET http://localhost:8057/accounts`
  - Détail d'un compte (avec client enrichi) : `GET http://localhost:8057/accounts/{id}`
  - Swagger UI : `http://localhost:8057/swagger-ui.html`
  - Console H2 : `http://localhost:8057/h2-console`

### 2. Accès via la Gateway (Port 9999)
Grâce au routage dynamique Eureka :
- `GET http://localhost:9999/CUSTOMER-SERVICE/customers`
- `GET http://localhost:9999/CUSTOMER-SERVICE/customers/1`
- `GET http://localhost:9999/EBANK-SERVICE/accounts`
- `GET http://localhost:9999/EBANK-SERVICE/accounts/{id}`

### 3. Tolérance aux Pannes (Resilience4j Circuit Breaker)
Lorsque `customer-service` est arrêté et qu'un appel est effectué sur `GET http://localhost:8057/accounts/{id}`, la méthode de fallback `defaultCustomerMethod` est automatiquement invoquée pour retourner un client par défaut (`Default name`, `default@gmail.com`) sans bloquer ni planter l'application.

### 4. Collection Postman
Une collection complète de requêtes est fournie dans le fichier [`postman-collection.json`](./postman-collection.json).
