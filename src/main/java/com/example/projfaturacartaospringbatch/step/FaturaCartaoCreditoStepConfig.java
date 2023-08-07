package com.example.projfaturacartaospringbatch.step;

import com.example.projfaturacartaospringbatch.model.FaturaCartaoCredito;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FaturaCartaoCreditoStepConfig {

    @Bean
    public Step faturaCartaoCreditoStep(JobRepository jobRepository,
                                        ItemReader<FaturaCartaoCredito> lerTransacoesReader,
                                        ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregarDadosClienteProcessor,
                                        ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito,
                                        PlatformTransactionManager transactionManager){

        return new StepBuilder("faturaCartaoCreditoStep", jobRepository)
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1, transactionManager)
                .reader(lerTransacoesReader)
                .processor(carregarDadosClienteProcessor)
                .writer(escreverFaturaCartaoCredito)
                .build();
    }
}
