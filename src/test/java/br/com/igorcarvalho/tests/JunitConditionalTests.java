package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @SpringBootTest incia o spring e roda
 * o teste dentro do spring container
 * */
//@SpringBootTest
class JunitConditionalTests {

    /**
     * @DisabledOnOs(OS.WINDOWS)
     * @EnabledOnOs(OS.LINUX)
     * @EnabledOnOs({ OS.LINUX, OS.MAC })
     * @DisabledOnOs({ OS.LINUX, OS.MAC })
     * Habilita ou desabilita o teste condicionalmente
     * baseado no sistema operacional da máquina q executa
     * os teste.
     * Aceita um ou mais sistemas como parametro.
     * */
    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void onLinuxOrMac() {
        // ...
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void onLinuxOnly() {
        // ...
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnWindows() {
        // ...
    }

    /**
     * @EnabledOnJre(JRE.JAVA_8), @DisabledOnJre(JRE.JAVA_8),
     * @EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 }),
     * @DisabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
     * Habilita ou desabilita o teste condicionalmente
     * baseado na versão da jre na maquina
     * */
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        // ...
    }

    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void disableOnlyOnJava8() {
        // ...
    }

    @Test
    @EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
    void onJava9Or10() {
        // ...
    }
    @Test
    @DisabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
    void disabledOnJava9Or10() {
        // ...
    }

    /**
     * @EnabledIfEnvironmentVariable(named = "USER", matches = "datagrupo"),
     * @DisabledIfEnvironmentVariable(named = "USER", matches = "datagrupo")
     * Habilita ou desabilita o teste condicionalmente
     * baseado na variavel de ambiente do sistema
     * no qual o teste roda. Na máquina usada no momento
     * que este teste foi escrito o $USER do ubunto era = datagrupo
     * */
    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "datagrupo")
    void onlyOnStagingServer() {
        // ...
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "USER", matches = "datagrupo")
    void notOnDeveloperWorkstation() {
        // ...
    }

}
