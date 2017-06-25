const Prometheus = require('prom-client')
const server = require('express')()

server.get('/prometheus', (req, res) => {
  res.end(Prometheus.register.metrics())
})

const PrometheusMetrics = {
  requestCounter: new Prometheus.Counter('throughput', 'The number of requests served'),
  homeCounter: new Prometheus.Counter('home_counter', 'The number of requests on home page'),
  aboutCounter: new Prometheus.Counter('about_counter', 'The number of requests on about page')
}

server.use((req, res, next) => {
  PrometheusMetrics.requestCounter.inc()
  next()
})

server.get('/', function (req, res) {
   PrometheusMetrics.homeCounter.inc()
   response = {
      message:"Welcome to service with node"
   };
   res.end(JSON.stringify(response));
})

server.get('/about', function (req, res) {
   PrometheusMetrics.aboutCounter.inc()
   response = {
      message:"Workshop of microservice"
   };
   res.end(JSON.stringify(response));
})


server.listen(process.env.PORT || 3000)
