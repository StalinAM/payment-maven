# Proyecto: Sistema de Pago con Soporte para Transacciones y Productos

Este proyecto es un sistema de pago basado en Java y Jakarta EE, diseñado para gestionar transacciones entre clientes, productos y métodos de pago.

---

## **Contenido**

1. [Configuración](#1-configuración-del-entorno)
2. [Endpoints](#endpoints)

---

## **Configuración del Entorno**

1. Clona este repositorio:

   ```bash
   git clone https://github.com/StalinAM/payment-maven.git
   cd payment-maven
   ```

2. Configura el archivo `persistence.xml` en `src/main/resources/META-INF/` con las credenciales de tu base de datos PostgreSQL:

   ```xml
   <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/paymentdb" />
   <property name="jakarta.persistence.jdbc.user" value="usuario" />
   <property name="jakarta.persistence.jdbc.password" value="contraseña" />
   ```

3. Acceder al sistema:
   - REST: `http://localhost:8080/payment-1.0-SNAPSHOT/api`
   - WSDL (SOAP): `http://localhost:8080/sistema-pagos/soap?wsdl`

---

## **Endpoints**

### **Cargar datos iniciales (clientes, productos, métodos de pago):**

```http
GET /crud/init-data/create
```

**Nota:** Si los datos existen mostrara los datos almacenados

### **Transacciones**

- **Obtener todas las transacciones:**

  ```html
  /transaction/all
  ```

  **Respuesta:**

  ```
    Transaction {
        id: 1,
        client: { id: 1, name: "John Doe" },
        products: [
            { id: 1, name: "Café Espresso", price: 10.5 }
            { id: 3, name: "Café Americano", price: 8.5 }
        ],
        amount: 19.0
    }
  ```

- **Procesar una nueva transacción:**

  ```http
  GET /transaction/process?clientId=1&productIds=2,3&paymentMethodId=1
  ```

  **Parámetros:**

  - clientId: ID del cliente.
  - productIds: IDs de los productos separados por coma (ejemplo: 1,2,3).
  - paymentMethodId: ID del método de pago.

### **Clientes**

- **Obtener todos los clientes:**

  ```http
  GET /crud/client/all
  ```

- **Crear un cliente:**

  ```http
  GET /crud/client/create?name=ejemplo&email=ejemplo@email.com
  ```

- **Actualizar un cliente:**

  ```http
  GET /crud/client/update?id=1&name=ejemplo&email=ejemplo@email.com
  ```

- **Obtener un cliente por ID:**
  ```http
  GET /crud/client/{id}
  ```

### **Productos**

- **Obtener todos los productos:**

  ```http
  GET /crud/product/all
  ```

- **Crear un producto:**

  ```http
  GET /crud/product/create?name=ejemplo&price=20.3
  ```

- **Actualizar un producto:**

  ```http
  GET /crud/product/update?id=1&name=ejemplo&price=19.2
  ```

- **Obtener un producto por ID:**
  ```http
  GET /crud/product/{id}
  ```

### **Métodos de Pago**

- **Obtener todos los métodos de pago:**
  ```http
  GET /crud/payment-methods
  ```
