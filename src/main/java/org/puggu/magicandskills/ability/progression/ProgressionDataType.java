package org.puggu.magicandskills.ability.progression;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.nio.ByteBuffer;

public class ProgressionDataType implements PersistentDataType<byte[], Progression> {

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Progression> getComplexType() {
        return Progression.class;
    }

    @Override
    public byte[] toPrimitive(Progression progression, PersistentDataAdapterContext context) {
        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + progression.getSequence().length() + 4 + progression.getAbilityKey().getKey().length());
        buffer.putInt(progression.getLevel());
        buffer.putInt(progression.getSequence().length());
        buffer.put(progression.getSequence().getBytes());
        buffer.putInt(progression.getAbilityKey().getKey().length());
        buffer.put(progression.getAbilityKey().getKey().getBytes());
        return buffer.array();
    }

    @Override
    public Progression fromPrimitive(byte[] bytes, PersistentDataAdapterContext context) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int level = buffer.getInt();
        int sequenceLength = buffer.getInt();
        byte[] sequenceBytes = new byte[sequenceLength];
        buffer.get(sequenceBytes);
        String sequence = new String(sequenceBytes);
        int keyLength = buffer.getInt();
        byte[] keyBytes = new byte[keyLength];
        buffer.get(keyBytes);
        NamespacedKey abilityKey = NamespacedKey.minecraft(new String(keyBytes));
        return new Progression(abilityKey, level, sequence);
    }
}
