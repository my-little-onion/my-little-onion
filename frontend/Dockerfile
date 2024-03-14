FROM node:18-alpine as build


WORKDIR /app

COPY package*.json ./

RUN yarn

COPY . .

RUN yarn build


FROM nginx:stable-alpine

RUN rm -rf /usr/share/nginx/html/*

COPY --from=build /app/dist /usr/share/nginx/html

RUN rm -rf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

EXPOSE 5173

CMD ["nginx", "-g", "daemon off;"]
