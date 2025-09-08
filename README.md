# Telegram Bot for JavaRush Community

Never miss a Java Rush update. Get tailored notifications for new articles that matter to you.

## 🚀 Quick Deployment
Getting started is straightforward with our automated deployment process:

Prerequisites:
- Terminal with bash support
- Docker
- Docker-compose

## Deployment Steps

- Create .env file
- Fill in the values
- Deploy the application by $bast deploy.sh
- Verify deployment by docker-compose ps

## ⚙️ Configuration
The .env file should contain your specific configuration:

- DB_USERNAME - Database authentication username
- DB_PASSWORD - Database authentication password
- DB_URL - Database server connection URL
- DB_NAME - Specific database name for the application
- BOT_TOKEN - Telegram Bot API authentication token

## 🎯 Bot Management

**Deploy the bot:**
```console
bash deploy.sh
```

**Start the bot:**
```console
bash start.sh
```

**Stop the bot:**
```console
bash stop.sh
```

**Restart the bot:**
```console
bash restart.sh
```



