/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Licensed under the Zeebe Community License 1.1. You may not use this file
 * except in compliance with the Zeebe Community License 1.1.
 */
package io.camunda.zeebe.example.decision;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;

public final class EvaluateDecisionCreator {

  public static void main(final String[] args) {
    final String defaultAddress = "localhost:26500";

    final ZeebeClientBuilder zeebeClientBuilder = ZeebeClient.newClientBuilder().credentialsProvider(new MyCredentialsProvider()).gatewayAddress(defaultAddress).usePlaintext();

    final String decisionId = "Decision_1aredx7";

    System.out.println("Evaluating decision");

    try (final ZeebeClient client = zeebeClientBuilder.build()) {

      final EvaluateDecisionResponse decisionEvaluation =
              client
                      .newEvaluateDecisionCommand()
                      .decisionId(decisionId)
                      .variables("{\"name\": \"Olek\"}")
                      .send()
                      .join();

      System.out.println("Decision evaluation result: " + decisionEvaluation.getDecisionOutput());

    }
    }
  }

