(defn create-table
  [table-name]
  (let [current (read-db) new (assoc current table-name {:data [] :indexes {}})]
    (write-db new)))


(defn drop-table
  [table-name]
  (let [current (read-db) new (dissoc current table-name)]
    write-db new))

(defn insert
  [table record id-key]
  (let [current (read-db)
        new (update-in current [table :data] conj record)
        index (- (count (get-in new [table :data])) 1)]
    ;; now we use assoc instead of conj, since conj returns a list,
    ;; resulting in :indexes being like this: {:name ({"Lemon" 1})}.
    ;; Instead, assoc returns a clean map. 
    (write-db
     (update-in new [table :indexes id-key] assoc (id-key record) index))))

(defn select-*
  [table-name]
  (let [db (read-db)]
    (get-in db [table-name :data])))


(defn select-*-where
  [table-name field field-value]
  (let [db (read-db)
        index ((get-in db [table-name :indexes field] field-value) field-value)
        data (get-in db [table-name :data])]
    (get data index)))

(defn insert-dedupe
  [table record id-key]
  (let [current (read-db)
        new (update-in current [table :data] conj record)
        index (- (count (get-in new [table :data])) 1)]
    ;; now we use assoc instead of conj, since conj returns a list,
    ;; resulting in :indexes being like this: {:name ({"Lemon" 1})}.
    ;; Instead, assoc returns a clean map. 
    (if (contains? (get-in current [table :indexes :name]) (:name record))
      (println "Record with this name already exists. Aborting.")
      (write-db
       (update-in new [table :indexes id-key] assoc (id-key record) index)))))

;;
(defn insert-dedupe-2
  ;; from the solution / nice use of select where function.
  ;; also, didn't know about if-let
  [table-name record id-key]  
  (if-let [existing-record (select-*-where table-name id-key (id-key record))]
    (println (str "Record with " id-key ": " (id-key record) " already exists. Aborting"))
    (let [db (read-db) 
          new-db (update-in db [table-name :data] conj record)
          index (- (count (get-in new-db [table-name :data])) 1)]
      (write-db 
       (update-in new-db [table-name :indexes id-key] assoc (id-key record) index)))))