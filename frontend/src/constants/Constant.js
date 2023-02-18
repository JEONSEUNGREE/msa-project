export const APIVERSION = "X-API-VERSION";
export const LOCAL_URI = "http://172.18.0.1:4001";
export const PREFIX_URI = "http://172.18.0.1";
export const APPLICATION = "application/json";
export const AUTH_TOKEN = "account_token";
export const REST_DOCS_API = `docs/index.html`;
export const CONFIG_SERVER_COMMON = `${PREFIX_URI}:10002/config-local/local`;
export const CONFIG_SERVER_GATEWAY = `${PREFIX_URI}:10002/gateway-local/local`;
export const GRAFANA_DASH_BOARD =
  "/d/4XuMd2Iiz/kubernetes-cluster-prometheus?orgId=1";

export const RABBITMQ_URI = `${PREFIX_URI}:1002`;
export const PROMETHEUS_URI = `${PREFIX_URI}:3018`;
export const GRAFANA_URI = `${PREFIX_URI}:3017${GRAFANA_DASH_BOARD}`;
export const ZIPKIN_URI = `${PREFIX_URI}:3011`;

export const USER_SERVICE_DOCS = `${LOCAL_URI}/user-service/${REST_DOCS_API}`;
export const ORDER_SERVICE_DOCS = `${LOCAL_URI}/order-service/${REST_DOCS_API}`;
export const PRODUCT_SERVICE_DOCS = `${LOCAL_URI}/product-service/${REST_DOCS_API}`;
