(ns dgc.core
  ""
  (:use [dgc ui util config read compat presets]
        [seesaw core])
  (:gen-class))

(native!)

(defn -main [& args]
  (let [puffballs (get-content "Dwarves.json")
        height    800
        content   (make-content puffballs)
        f         (doto (frame 
                          :title      "Dwarven Guidance Councilor" 
                          :height     height
                          :width      (* height 1.618)
                          :content    content
                          :on-close   :hide) ;:exit)
                        show!
                        ; load puffballs
                        (update-content! puffballs))]
    
    ; some initial selections for easy debugging
    (selection! (select f [:#dwarf-list]) {:multi? true} [(first puffballs)])
    ;(selection! (select f [:#dwarf-list]) {:multi? true} [(first puffballs) (second puffballs)])
    ;(selection! (select f [:#prof-list]) {:multi? true} [(first professions) (second professions)])

    ;return the frame
    f
))

; If called on the command line
(if *command-line-args*
  (apply -main *command-line-args*))
