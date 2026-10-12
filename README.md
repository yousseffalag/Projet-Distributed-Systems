# Projet : E-Bank Microservices Architecture (Partie 1 & Partie 2)

Projet d'architecture distribuée pour une application bancaire (E-Bank) basé sur **Spring Cloud**, **OpenFeign**, **Resilience4j**, **Spring AI**, le protocole **MCP (Model Context Protocol)** et un client conversationnel **Telegram**.

Développé par : **Falag Youssef** (`com.falag.youssef`)

---

## 🏛️ Architecture et Microservices

| Service | Port | Description |
| :--- | :--- | :--- |
| **`discovery-service`** | `8761` | Serveur d'enregistrement Netflix Eureka |
| **`customer-service`** | `8056` | Gestion des clients + Serveur MCP Tools |
| **`ebank-service`** | `8057` | Gestion des comptes, OpenFeign, Circuit Breaker + Serveur MCP Tools |
| **`gateway-service`** | `9999` | API Gateway avec routage dynamique |
| **`ebank-bot`** | `8058` | Agent IA (Spring AI OpenAI), Client MCP, contrôleur REST et bot Telegram |

---

## ⚙️ Configuration & Variables d'Environnement

Un fichier template [`.env.example`](./.env.example) est fourni à la racine :
```properties
# Clé d'API OpenAI
OPENAI_API_KEY=sk-proj-your-openai-api-key-here

# Clé Telegram (Créer un bot via @BotFather)
TELEGRAM_BOT_USERNAME=EbankFalagBot
TELEGRAM_BOT_TOKEN=123456789:ABCdefGhIJKlmNoPQRsTUVwxyZ
```

---

## 🚀 Ordre de Démarrage des Services

1. **`discovery-service`** (port 8761)
2. **`customer-service`** (port 8056)
3. **`ebank-service`** (port 8057)
4. **`gateway-service`** (port 9999)
5. **`ebank-bot`** (port 8058)

---

## 🧪 Documentation et Tests du Chatbot

### 1. Test du Chatbot avec un Client REST
Un fichier de requêtes exécutables [`ebank-bot/rest-client-tests.http`](./ebank-bot/rest-client-tests.http) est fourni pour tester les interactions REST :
- `GET http://localhost:8058/chat?question=Donne-moi la liste de tous les clients`
- `POST http://localhost:8058/chat` (avec payload JSON `{ "question": "..." }`)
- Tests d'intégration automatisés : `EbankChatbotControllerTest.java`.

### 2. Test du Chatbot avec un Client Telegram
Le microservice `ebank-bot` intègre un client Telegram (`TelegramBot.java`) connecté en continu via Long Polling :
1. Créez un bot sur Telegram via **@BotFather** et récupérez le Token.
2. Définissez `TELEGRAM_BOT_TOKEN` et `TELEGRAM_BOT_USERNAME` dans votre environnement ou fichier `.env`.
3. Démarrez `ebank-bot`.
4. Ouvrez Telegram, lancez une conversation avec votre bot et posez vos questions bancaires (ex : *"Quels sont mes comptes ?"*). Le bot interrogera l'agent IA et les outils MCP pour vous répondre instantanément.
