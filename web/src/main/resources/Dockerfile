FROM nginx
RUN mkdir /data/ -p
RUN chown nginx.nginx -R /data/
ADD nginx.conf /etc/nginx/nginx.conf
COPY ./api/* /data/api/
COPY ./mvc/* /data/mvc/
COPY ./static/* /data/static/
EXPOSE 80
ENTRYPOINT nginx -g "daemon off;"
