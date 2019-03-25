package net.helix.sbx.model;

import net.helix.sbx.controllers.TransactionViewModel;
import net.helix.sbx.controllers.TransactionViewModelTest;
import net.helix.sbx.crypto.SpongeFactory;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HashTest {
    @Test
    public void calculate() throws Exception {
        Hash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, TransactionViewModelTest.getRandomTransactionBytes());
        Assert.assertNotEquals(0, hash.hashCode());
        Assert.assertNotEquals(null, hash.bytes());
    }

    @Test
    public void calculate1() throws Exception {
        Hash hash = TransactionHash.calculate(TransactionViewModelTest.getRandomTransactionBytes(), 0, 128, SpongeFactory.create(SpongeFactory.Mode.S256));
        Assert.assertNotEquals(null, hash.bytes());
        Assert.assertNotEquals(0, hash.hashCode());
    }

    @Test
    public void calculate2() throws Exception {
        byte[] bytes = TransactionViewModelTest.getRandomTransactionBytes();
        TransactionHash hash = TransactionHash.calculate(bytes, 0, TransactionViewModel.ADDRESS_SIZE, SpongeFactory.create(SpongeFactory.Mode.S256));
        Assert.assertNotEquals(0, hash.hashCode());
        Assert.assertNotEquals(null, hash.bytes());
    }

    @Test
    public void trailingZeros() throws Exception {
        Hash hash = TransactionHash.NULL_HASH;
        Assert.assertEquals(TransactionHash.SIZE_IN_BYTES, hash.trailingZeros());
    }
    @Test
    public void leadingZeros() throws Exception {
        Hash hash = TransactionHash.NULL_HASH;
        Assert.assertEquals(Hash.SIZE_IN_BYTES, hash.leadingZeros());
    }

    @Test
    public void bytes() throws Exception {
        TransactionHash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, TransactionViewModelTest.getRandomTransactionBytes());
        Assert.assertFalse(Arrays.equals(new byte[Hash.SIZE_IN_BYTES], hash.bytes()));
    }

    @Test
    public void equals() throws Exception {
        byte[] bytes = TransactionViewModelTest.getRandomTransactionBytes();
        TransactionHash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, bytes);
        TransactionHash hash1 = TransactionHash.calculate(SpongeFactory.Mode.S256, bytes);
        Assert.assertTrue(hash.equals(hash1));
        Assert.assertFalse(hash.equals(Hash.NULL_HASH));
        Assert.assertFalse(hash.equals(TransactionHash.calculate(SpongeFactory.Mode.S256, TransactionViewModelTest.getRandomTransactionBytes())));
    }

    @Test
    public void hashCodeTest() throws Exception {
        byte[] bytes = TransactionViewModelTest.getRandomTransactionBytes();
        TransactionHash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, bytes);
        Assert.assertNotEquals(hash.hashCode(), 0);
        // TODO Find actual value for this assert
        //Assert.assertEquals(Hash.NULL_HASH.hashCode(), -240540129);
    }

    @Test
    public void toHexStringTest() throws Exception {
        byte[] bytes = TransactionViewModelTest.getRandomTransactionBytes();
        TransactionHash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, bytes);
        Assert.assertEquals(Hex.toHexString(Hash.NULL_HASH.bytes()), "0000000000000000000000000000000000000000000000000000000000000000");
        Assert.assertNotEquals(Hex.toHexString(hash.bytes()), "0000000000000000000000000000000000000000000000000000000000000000");
        Assert.assertNotEquals(Hex.toHexString(hash.bytes()).length(), 0);
    }


    @Test
    public void txBytes() throws Exception {
        byte[] bytes = TransactionViewModelTest.getRandomTransactionBytes();
        TransactionHash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, bytes);
        Assert.assertTrue(Arrays.equals(new byte[Hash.SIZE_IN_BYTES], Hash.NULL_HASH.bytes()));
        Assert.assertFalse(Arrays.equals(new byte[Hash.SIZE_IN_BYTES], hash.bytes()));
        Assert.assertNotEquals(0, hash.bytes().length);
    }

    @Test
    public void compareTo() throws Exception {
        byte[] randomTransactionBytes = TransactionViewModelTest.getRandomTransactionBytes();
        TransactionHash hash = TransactionHash.calculate(SpongeFactory.Mode.S256, randomTransactionBytes);
        Assert.assertEquals(hash.compareTo(Hash.NULL_HASH), -Hash.NULL_HASH.compareTo(hash));
    }

}