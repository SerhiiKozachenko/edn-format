(ns edn_format.core
  (:require [clojure.browser.event :as event]
            [clojure.browser.dom   :as dom]
            [cljs.reader :refer [read-string]]
            [cljs.pprint :refer [pprint]]))


(defonce dirty (dom/get-element "dirty"))


(defonce clean (dom/get-element "clean"))


(event/listen dirty "blur"
              (fn []
                (try
                  (if-some [edn (read-string dirty/value)]
                    (let [edn-pretty-str (with-out-str (pprint edn))]
                      (set! clean/value edn-pretty-str))
                    (set! clean/value ""))
                  (catch js/Error ex
                    (js/alert (ex-message ex))))))


(defn on-js-reload [])
