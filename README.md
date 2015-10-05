# modern-clj-web

This project shows my opinionated way to develop Clojure web applications in late 2015.

It embraces Clojure philosophy to compose small libraries over frameworks.
On the frontend it uses ClojureScript with [Om](https://github.com/omcljs/om) and [om-tools](https://github.com/Prismatic/om-tools) to reduce some verbosity.
The backend is a [Ring](https://github.com/ring-clojure/ring) web server which exposes REST backend with Mongo as repository,
all wired with [component](https://github.com/stuartsierra/component).  
It was created from [duct](https://github.com/weavejester/duct) template and should follow [12-factor](http://12factor.net) philosophy,
but it's main focus is rather on interactive development with
[reloaded workflow](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded)
and [figwheel](https://github.com/bhauman/lein-figwheel).  

## Developing

To begin developing, start with a REPL.

```sh
lein repl
```

Run `go` to initiate and start the system.

```clojure
user=> (go)
:started
```

By default this creates a web server at <http://localhost:3000>.

When you make changes to your source files, use `reset` to reload any
modified files and restart the server.

```clojure
user=> (reset)
:reloading (...)
:started
```

If you want to reload your server periodically you can put (reset) in while loop:

```clojure
user=> (while true (do (Thread/sleep 1000)(reset)))
```

To develop frontend, start `fighweel` on another terminal.

```sh
lein figwheel
```

When you open main page in the browser `fighweel` should establish websocket connection and reload any changes in ClojureScript files.  

## Legal

Copyright © 2015 Piotr Jagielski
