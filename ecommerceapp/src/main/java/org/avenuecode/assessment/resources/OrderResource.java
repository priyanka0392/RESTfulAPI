package org.avenuecode.assessment.resources;

import org.avenuecode.assessment.Model.OrderProduct;
import org.avenuecode.assessment.Model.Product;
import org.avenuecode.assessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
@Produces("application/json")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    /*Provides a method to list all the orders placed in JSON format.
     It contains the details of the order such as order id, date and the
     list of all products that have been ordered.*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders()
    {
        List<OrderProduct> orderProducts = orderService.getAllOrders();
        return Response.status(Response.Status.fromStatusCode(200)).entity(orderProducts).build();
    }

    /*This allows you to view the order by the order id. It takes in an input of the order id and lets you view
       all the details of a particular order with the list of the products that have been ordered.*/
    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@PathParam("orderId") int orderId)
    {
        List<OrderProduct> orderProducts = orderService.getOrder(orderId);
        return Response.status(Response.Status.fromStatusCode(200)).entity(orderProducts).build();
    }

    /* This functions deletes an order on a given id. You need to pass the id of an order to delete the order
    from the list. */
    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("orderId") int orderId)
    {
        orderService.deleteOrder(orderId);
        return Response.status(Response.Status.fromStatusCode(204)).build();
    }

    /*This function lets you update an order when you send in an information of a order in a JSON format.
      You can update the quantity of a particular product in the order and add a new product from the catalog   . */

    @PUT
    @Path("/{orderId}/{productId}")
    @ResponseBody
    public Response updateOrder(@PathParam("orderId") int orderId,@PathParam("productId") int productId,@RequestBody Product product)
    {
        OrderProduct orderProduct = orderService.updateOrder(orderId,productId,product.getQuantity());
        return Response.status(Response.Status.fromStatusCode(204)).entity(orderProduct).build();
    }

    /*You can update the details of an order by deleting a product. It returns a status code of 204
    which represents no-content. Delete function will not return anything in the body and hence a 204
    status code
     */
    @DELETE
    @Path("/{orderId}/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderWithProductDelete(@PathParam("orderId")int orderId,@PathParam("productId")int productId)
    {
        List<OrderProduct> order = orderService.deleteByOrderProduct(orderId,productId);
        return Response.status(Response.Status.fromStatusCode(204)).entity(order).build();
    }

    /*This function allows you to place an order. This takes in an input in the JSON format in a set of
    products with the product information. You are allowed to a product from the product catalog.
    It returns a status of CREATED which means that the order has been created successfully. */
    @POST
    @ResponseBody
    public Response addOrder(@RequestBody OrderProduct order)
    {
        OrderProduct order1 = orderService.addOrder(order);
        return Response.status(Response.Status.CREATED).entity(order1).build();
    }

}
