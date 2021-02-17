# Nextjs Demo using Server side Firebase session

## Installation

Add .env file in the project root folder with values from firebase config json and spring boot host and port

```
NEXT_PUBLIC_API_KEY=REPLACE_WITH_API_KEY
NEXT_PUBLIC_AUTH_DOMAIN=REPLACE_WITH_AUTH_DOMAIN
NEXT_PUBLIC_DB_URL=REPLACE_WITH_DB_URL
NEXT_PUBLIC_PROJECT_ID=REPLACE_WITH_PROJECT_ID
NEXT_PUBLIC_APP_ID=REPLACE_WITH_APP_ID
NEXT_PUBLIC_MIDDLEWARE_URL=REPLACE_WITH_SPRING_BOOT_URL
NEXT_PUBLIC_TEST_LOGIN=false
```

First, run the development server:

```bash
npm run dev
# or
yarn dev
```

cypress end to end test

```bash
npm run it
# or
yarn it
```

Open [http://localhost:4000](http://localhost:4000) with your browser to see the result.
