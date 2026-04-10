### Task Overview
Utkrusht is preparing a lightweight product catalog API that internal teams will use to look up product information and derived pricing in real time. The current Spring Boot application has a basic project structure with an entity, repository, service shell, and controller shell, but key behaviors like creating products, fetching enriched product details, and handling simple asynchronous work are not yet wired together. Your work will help ensure the service can reliably serve product details.

### Objectives
- Ensure that a client can create a new product by sending structured data to the API, with the product being stored in the underlying data store and an appropriate representation returned.
- Provide a way for clients to retrieve the details of a single product.
- Route external requests through a controller that delegates to a dedicated service layer, which in turn uses a repository to interact with the database.
- Implement service logic that coordinates data retrieval and price calculation without placing heavy work directly in the controller layer.
- Introduce simple asynchronous behavior for part of the work related to product details or price calculation so that long-running operations do not unnecessarily block processing.
- Apply basic error handling so that invalid product identifiers or missing data result in clear and consistent HTTP responses instead of generic failures.
- Keep the overall design structured so that it is straightforward to extend with additional endpoints or pricing strategies in the future.

### How to Verify
- Trigger the product creation endpoint with valid input and confirm that the product is stored and that subsequent reads return consistent data from the database.
- Request the details for an existing product and verify that the response contains the expected fields and that the price value reflects the logic defined in the business layer.
- Attempt to fetch a product that does not exist and observe that the API returns an appropriate HTTP status and message rather than an unhandled error.
- Exercise the part of the system that performs work asynchronously and confirm that it behaves predictably under repeated calls, without blocking unrelated operations.
- Inspect logs or console output to ensure the application starts cleanly, handles requests without stack traces for normal scenarios, and surfaces errors in a controlled way.

### Helpful Tips
- Consider how responsibilities should be split between request handling, business logic, and data access so each layer remains focused and testable.
- Think about how incoming HTTP requests should be translated into method calls and how responses should represent the product data and derived values.
- Explore how Spring manages the lifecycle of components so that shared collaborators are reused efficiently instead of recreated for every call.
- Review how to persist and retrieve entities using a repository abstraction so that the service layer does not depend on low-level database details.
- Look into how to introduce non-blocking or background work for operations that do not need to hold up the entire request, while still keeping error handling clear.
