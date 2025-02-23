FROM node:20-alpine AS build

WORKDIR /build

COPY . .

RUN npm install -g @angular/cli

RUN npm install

RUN ng build --configuration production

FROM nginx:latest

WORKDIR /app

COPY default.conf /etc/nginx/conf.d/default.conf

COPY --from=build /build/dist/web-application/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]