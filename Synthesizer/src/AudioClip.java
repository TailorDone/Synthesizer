import java.util.Arrays;

public class AudioClip {
    public final static double DURATION = 2.0;
    public final static int SAMPLE_RATE = 44100;
    public final static int NUM_SAMPLES = (int)DURATION * SAMPLE_RATE;
    private byte[] byteArray;


    public AudioClip(short[] shortArray){
        byteArray = new byte[shortArray.length * 2];
        for(int i = 0; i < shortArray.length; i++){
            setSample(i,shortArray[i]);
        }
    }

    public AudioClip(){
        byteArray = new byte[SAMPLE_RATE *4];
    }

    public int getSample(int index){
        int upperByte = byteArray[index*2+1] << 8;
        int lowerByte =  byteArray[index*2];
        lowerByte = lowerByte & 0xFF;
        int sample = upperByte | lowerByte;
        return sample;
    }

    public void setSample(int index, int value){
        value = Math.max(Short.MIN_VALUE, Math.min(value, Short.MAX_VALUE));
        int upperByte = value >>> 8;
        byteArray[2*index] = (byte)value;
        byteArray[2*index+1] = (byte)(upperByte);
    }

    public byte[] getData(){
        return Arrays.copyOf(byteArray, byteArray.length);
    }
}
