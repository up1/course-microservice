package main

import (
    "encoding/json"
    "fmt"
    "net/http"

		"github.com/prometheus/client_golang/prometheus"
		"github.com/prometheus/client_golang/prometheus/promhttp"
)

var httpResponsesTotal = prometheus.NewCounterVec(
  prometheus.CounterOpts{
    Namespace: "demo_service",
        Subsystem: "http_server",
        Name:      "http_responses_total",
        Help:      "The count of http responses issued, classified by code and method.",
    },
    []string{"code", "method"},
)

var homeCounterTotal = prometheus.NewCounterVec(
  prometheus.CounterOpts{
    Namespace: "demo_service",
        Subsystem: "http_server",
        Name:      "home_counter",
        Help:      "The counter of home page",
    },
		nil,
)

var aboutCounterTotal = prometheus.NewCounterVec(
  prometheus.CounterOpts{
    Namespace: "demo_service",
        Subsystem: "http_server",
        Name:      "about_counter",
        Help:      "The counter of about page",
    },
		nil,
)

func init() {
  prometheus.MustRegister(httpResponsesTotal)
	prometheus.MustRegister(homeCounterTotal)
	prometheus.MustRegister(aboutCounterTotal)
}

func main() {
    http.HandleFunc("/", handler)
    http.HandleFunc("/about/", about)
		http.Handle("/prometheus/", promhttp.Handler())
    http.ListenAndServe(":8080", nil)
}

func handler(w http.ResponseWriter, r *http.Request) {
	  httpResponsesTotal.WithLabelValues("200", r.Method).Inc()
		homeCounterTotal.WithLabelValues().Inc()
    fmt.Fprintf(w, "Welcome to golang service, %!", r.URL.Path[1:])
}

type Message struct {
    Text string
}

func about (w http.ResponseWriter, r *http.Request) {
		httpResponsesTotal.WithLabelValues("200", r.Method).Inc()
		aboutCounterTotal.WithLabelValues().Inc()
    m := Message{"Welcome to My API, build v0.0.1."}
    b, err := json.Marshal(m)

    if err != nil {
        panic(err)
    }

     w.Write(b)
}
