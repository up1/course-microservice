const server = require('express')()

server.get('/', function (req, res) {
   response = {
      message:"Welcome to service with node"
   };
   res.end(JSON.stringify(response));
})

server.get('/about', function (req, res) {
   response = {
      message:"Workshop of microservice"
   };
   res.end(JSON.stringify(response));
})


server.listen(process.env.PORT || 3000)
