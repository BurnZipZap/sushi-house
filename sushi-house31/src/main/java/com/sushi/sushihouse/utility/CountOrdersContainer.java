package com.sushi.sushihouse.utility;

import java.util.List;

import com.sushi.sushihouse.entity.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CountOrdersContainer {
	int countUser;
	int countManager;
	int countCook;
	int countCookProblem;
	int countCookNotProblem;
	int countDelivery;
	int countDeliveryProblem;
	int countDeliveryNotProblem;
	int countAll;
	int countProgress;
	
	public CountOrdersContainer(List<Order> orders){
		for (Order order : orders) {
			if(order.getStatus().equals(StatusOrder.USER_MADE_AN_ORDER.name())) {
				countUser++;
			}
			if(order.getStatus().equals(StatusOrder.CONFIRMED_MANAGER.name())) {
				countManager++;
			}
			if(order.getStatus().equals(StatusOrder.COOK_ORDER_IS_READY.name())) {
				countCook++;
			}
			if(order.getStatus().equals(StatusOrder.COOK_PROBLEM.name())) {
				countCookProblem++;
			}
			if(order.getStatus().equals(StatusOrder.COOK_NOT_PROBLEM.name())) {
				countCookNotProblem++;
			}
			if(order.getStatus().equals(StatusOrder.DELIVERED_ORDER.name())) {
				countDelivery++;
			}
			if(order.getStatus().equals(StatusOrder.DELIVERY_PROBLEM.name())) {
				countDeliveryProblem++;
			}
			if(order.getStatus().equals(StatusOrder.DELIVERY_NOT_PROBLEM.name())) {
				countDeliveryNotProblem++;
			}
			countProgress=countUser+countManager+countCook+countCookProblem+countCookNotProblem
					+countDeliveryProblem+countDeliveryNotProblem;
			countAll=countProgress+countDelivery;	
		}
	}

	
	
}
