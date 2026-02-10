# BFHL API - REST API Challenge

A Spring Boot REST API providing mathematical operations (Fibonacci, Prime, LCM, HCF) and AI-powered question answering using Google Gemini 2.5 Flash.

## 📋 Features

- **POST /bfhl**: Handle fibonacci, prime, lcm, hcf, and AI operations
- **GET /health**: Health check endpoint
- Input validation (exactly one operation per request)
- Standardized JSON responses with is_success, official_email, data fields
- Google Gemini 2.5 Flash AI integration
- CORS enabled for frontend integration
- Dockerized for easy deployment
- Maven wrapper included (no local Maven installation required)

## 🛠️ Tech Stack

- Java 17 (compatible with Java 21)
- Spring Boot 3.2.0
- Spring WebFlux (for AI API calls)
- Maven 3.9.5 (via wrapper)
- Google Gemini 2.5 Flash API
- Lombok (for cleaner code)
- Docker (multi-stage build)

## 📦 API Endpoints

### GET /health
Returns health status of the API.

**Response:**
```json
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in"
}
```

### POST /bfhl
Processes mathematical operations or AI questions.

**Request Examples:**

1. **Fibonacci:**
```json
{
  "fibonacci": 7
}
```
Response:
```json
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": [0, 1, 1, 2, 3, 5, 8]
}
```

2. **Prime Numbers:**
```json
{
  "prime": [2, 4, 7, 9, 11]
}
```
Response:
```json
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": [2, 7, 11]
}
```

3. **LCM:**
```json
{
  "lcm": [12, 18, 24]
}
```
Response:
```json
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": 72
}
```

4. **HCF:**
```json
{
  "hcf": [24, 36, 60]
}
```
Response:
```json
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": 12
}
```

5. **AI Question:**
```json
{
  "AI": "What is the capital city of Maharashtra?"
}
```
Response:
```json
{
  "is_success": true,
  "official_email": "your.email@chitkara.edu.in",
  "data": "Mumbai"
}
```

## 🔑 Prerequisites

1. **Java**: JDK 17 or higher
2. **Google Gemini API Key**:
   - Visit https://aistudio.google.com
   - Sign in with your Google account
   - Click **Get API Key** → Create API key
   - Copy the API key (starts with `AIza...`)
3. **GQuick Start (Local Development)

### Windows:
```cmd
set GEMINI_API_KEY=your-api-key-here
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
mvnw.cmd spring-boot:run
```

### Linux/Mac:
```bash
export GEMINI_API_KEY=your-api-key-here
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📦 Deployment to Render

### Method 1: Using Render CLI (Automated)

1. **Install Render CLI:**
   ```bash
   npm install -g render-cli
   ```

2. **Login to Render:**
   ```bash
   render login
   ```

3. **Initialize Git and push to GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: BFHL API"
   gh repo create bfhl-api --public --source=. --remote=origin
   git push -u origin main
   ```

4. **Deploy to Render:**
   ```bash
   render deploy
   ```

### Method 2: Using Render Dashboard

1. **Push to GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: BFHL API"
   git remote add origin https://github.com/yourusername/bfhl-api.git
   git branch -M main
   git push -u origin main
   ```

2. **Deploy on Render:**
   - Visit https://render.com and sign up/login
   - Click **New** → **Web Service**
   - Connect your GitHub repository
   - Configure:
     - **Name**: bfhl-api
     - **Environment**: Docker
     - **Region**: Choose nearest
     - **Branch**: main
     - **Build Command**: (leave empty)
     - **Start Command**: (leave empty)
   - **Environment Variables**:
     - `GEMINI_API_KEY`: `AIzaSyALGBRzVsHdycRP8biq_XyRFmbLpz2T15Y`
     - `APP_OFFICIAL_EMAIL`: `sahil3920.beai23@chitkara.edu.in`
   - Click **Create Web Service**
   - Wait 3-5 minutes for deployment
   - Copy the public URL (e.g., `https://bfhl-api.onrender.com`)

### Method 3: Using Railway

1. **Push to GitHub** (same as above)

2. **Deploy on Railway:**
   - Visit https://railway.app
   - Sign in with GitHub
   - **New Project** → **Deploy from GitHub repo**
   - Select your repository
   - Add environment variables:
     - `GEMINI_API_KEY`: Your API key
     - `APP_OFFICIAL_EMAIL`: Your email
   - Railway auto-detects Dockerfile and deploys
   - Copy the public URL from Railway dashboard

## 🐳 Docker Deployment

### Build and run locally:
```bash
docker build -t bfhl-api .
docker run -p 8080:8080 \
  -e GEMINI_API_KEY=your-api-key \
  -e APP_OFFICIAL_EMAIL=your.email@chitkara.edu.in \
  bfhl-api
```

### Push to Docker Hub:
```bash
docker build -t yourusername/bfhl-api .
docker login
docker push yourusername/bfhl-api
```
   ngrok http 8080
   ```
   Copy the ngrok URL (e.g., https://xyz123.ngrok.io)

## 🧪 Testing the API

### Using curl:

```bash
# Health check
curl https://your-deployed-url/health

# Test Fibonacci
curl -X POST https://your-deployed-url/bfhl \
  -H "Content-Type: application/json" \
  -d '{"fibonacci": 7}'

# Test Prime
curl -X POST https://your-deployed-url/bfhl \
  -H "Content-Type: application/json" \
  -d '{"prime": [2,4,7,9,11]}'

# Test LCM
curl -X POST https://your-deployed-url/bfhl \
  -H "Content-Type: application/json" \
  -d '{"lcm": [12,18,24]}'

# Test HCF
curl -X POST https://your-deployed-url/bfhl \
  -H "Content-Type: application/json" \
  -d '{"hcf": [24,36,60]}'

# Test AI
curl -X POST https://your-deployed-url/bfhl \
  -H "Content-Type: application/json" \
  -d '{"AI": "What is the capital city of Maharashtra?"}'
```

### Using Postman:

1. Import the collection or create requests manually
2. Set `Content-Type: application/json` in headers
3. Send POST requests to `/bfhl` with appropriate body
4. Send GET request to `/health`

## ⚠️ Error Handling

The API returns appropriate HTTP status codes:
- `200 OK`: Successful operation
- `400 Bad Request`: Invalid input or validation error
- `500 Internal Server Error`: Server-side errors

Error response format:
```json
{
  "is_success": false,
  "official_email": "your.email@chitkara.edu.in",
  "data": null,
  "error": "Error message here"
}
```

## 📁 Project Structure

```
bfhl-api/
**application.properties** (already configured):
```properties
app.official.email=sahil3920.beai23@chitkara.edu.in
gemini.api.key=${GEMINI_API_KEY:default-key}
server.port=8080
```

**Environment Variables** (set during deployment):
- `GEMINI_API_KEY`: Your Google Gemini API key (required)
- `APP_OFFICIAL_EMAIL`: Override email (optional
│       │   └── service/
│       │       ├── MathService.java
│       │       └── AIService.java
│       └── resources/
│           └── application.properties
├── Dockerfile
├── pom.xml
└── README.md
```

## 🔒 Security Features

- Input validation for all operations
- Error messages don't expose internal details
- Environment variables for sensitive data
- No hardcoded credentials

## 📝 Configuration

Update `src/main/resources/application.properties`:

```properties
app.official.email=your.email@chitkara.edu.in
```

Set environment variables during deployment:
- `GEMINI_API_KEY`: Your Google Gemini API key
- `APP_OFFICIAL_EMAIL`: Your Chitkara email (optional, overrides properties file)

## 🐛 Troubleshooting
Windows: Maven wrapper fails
```cmd
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
mvnw.cmd clean compile spring-boot:run
```

### AI endpoint returns errors
- Verify `GEMINI_API_KEY` is set correctly
- Check API key at https://aistudio.google.com
- Current model: `gemini-2.5-flash` (automatically selected)

### Port 8080 already in use
```bash
# Find and kill process using port 8080
netstat -ano | findstr :8080
taskkill /PID <process-id> /F
```� API Validation Rules

- Each request must contain **exactly one** operation key
- Multiple keys in one request returns `400 Bad Request`
- Fibonacci: positive integer
- Prime: array of positive integers
- LCM/HCF: array of at least 2 positive integers
- AI: non-empty string question

## 🔗 Live Demo

After deployment, your API will be accessible at:
- **Render**: `https://your-app-name.onrender.com`
- **Railway**: `https://your-app-name.up.railway.app`

Test it:
```bash
curl https://your-app-name.onrender.com/health
```

## 📄 License

Educational project for Chitkara University - BFHL Challenge

## 👨‍💻 Author

**Sahil** - sahil3920.beai23@chitkara.edu.in  
Chitkara University, Computer Science Engineering

---

**Note**: The API uses Google Gemini 2.5 Flash for AI question answering. Make sure to set your `GEMINI_API_KEY` environment variable before deployment.n for downloading dependencies

## 📄 License

This project is for educational purposes.

## 👨‍💻 Author

Chitkara University Student
