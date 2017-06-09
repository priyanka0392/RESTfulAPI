package org.avenuecode.assessment.resources;


import org.avenuecode.assessment.Model.Product;
import org.avenuecode.assessment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Produces("application/json")
public class ProductResource {

    @Autowired
    private ProductService productService;

    /* This functions lists all the products from the product catalog.It returns a status 200 after
     successful completion of getting the list of products  */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        return Response.status(Response.Status.fromStatusCode(200)).entity(products).build();
    }

    /* This functions lets you view a product on a particular id. Takes an id on which lists
    the product information of the given id. It returns a status 200 after
     successful completion of getting the product */
    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") int productId)
    {
        Product product = productService.getProduct(productId);
        return Response.status(Response.Status.fromStatusCode(200)).entity(product).build();
    }

    /* This function lets you add a product in the catalog. You need to provide all the information of the product
    in a JSON format which will save the product in the catalog. It returns a status 201 which
    represents a create status that a product has been added successfully. */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product)
    {
      Product product1 = productService.saveProduct(product);
      return Response.status(Response.Status.CREATED).entity(product1).build();
    }

}


